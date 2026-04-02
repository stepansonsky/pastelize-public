package pastelize.android

import android.os.Build
import io.github.muntashirakon.adb.AbsAdbConnectionManager
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import java.io.ByteArrayInputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.Security
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages a wireless ADB connection to the local device for executing shell commands.
 *
 * This class handles the TLS authentication required by Android's wireless debugging.
 * On first use, it generates an RSA 2048-bit key pair and a self-signed X.509 certificate
 * using BouncyCastle, then persists them in the Android KeyStore. Subsequent launches
 * reuse the stored credentials, avoiding repeated pairing prompts.
 *
 * Extends [AbsAdbConnectionManager] from libadb-android, which provides the underlying
 * ADB protocol implementation (connection, pairing, and stream management).
 */
@Singleton
class AdbConnectionManager @Inject constructor() : AbsAdbConnectionManager() {

    private val privateKey: PrivateKey
    private val certificate: Certificate

    companion object {
        /** Alias under which the RSA key pair is stored in the Android KeyStore. */
        private const val KEY_ALIAS = "PastelizeAdbKey"
    }

    init {
        // Register BouncyCastle as a security provider if not already present.
        // Required for RSA key generation and X.509 certificate creation outside
        // the Android KeyStore (which doesn't support direct key pair generation
        // with BouncyCastle-signed certificates).
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(BouncyCastleProvider())
        }

        // Inform the parent class of the current Android API level so it can
        // adapt the ADB protocol behavior accordingly.
        api = Build.VERSION.SDK_INT

        val ks = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }

        if (ks.containsAlias(KEY_ALIAS)) {
            // Reuse existing credentials — the device has already been paired
            // with this key, so no new pairing prompt will be shown.
            val entry = ks.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
            privateKey = entry.privateKey
            certificate = entry.certificate
        } else {
            // Generate a new RSA 2048-bit key pair for ADB TLS authentication.
            val keyPair = KeyPairGenerator.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME)
                .apply { initialize(2048, SecureRandom()) }
                .generateKeyPair()
            privateKey = keyPair.private

            // Build a self-signed X.509 certificate valid for 10 years.
            // ADB uses this certificate to identify the client during the TLS handshake.
            val subject = X500Name("CN=Pastelize")
            val serial = BigInteger.valueOf(System.currentTimeMillis())
            val notBefore = Date()
            val notAfter = Date(System.currentTimeMillis() + 10L * 365 * 24 * 60 * 60 * 1000)

            // Convert the BouncyCastle certificate holder to a standard Java Certificate
            // so it can be stored in the Android KeyStore.
            val certHolder = JcaX509v3CertificateBuilder(
                subject, serial, notBefore, notAfter, subject, keyPair.public
            ).build(JcaContentSignerBuilder("SHA512withRSA").build(privateKey))

            certificate = CertificateFactory.getInstance("X.509")
                .generateCertificate(ByteArrayInputStream(certHolder.encoded))

            // Persist the key pair and certificate in the Android KeyStore for future sessions.
            val chain = arrayOf(certificate)
            ks.setKeyEntry(KEY_ALIAS, privateKey, null, chain)
        }
    }

    /**
     * Sets the display saturation level via ADB shell.
     *
     * @param saturation value from 0 (full color) to 100 (fully desaturated / grayscale).
     * @return `true` if the command was sent successfully, `false` otherwise.
     */
    fun setSaturation(saturation: Int): Boolean =
        sendCommand("cmd color_display set-saturation $saturation")

    /**
     * Executes a shell command over the active ADB connection.
     *
     * Opens an ADB shell stream, which triggers command execution on the device.
     * The stream is closed immediately after — we don't need to read the output.
     *
     * @return `true` if the command was dispatched without error, `false` otherwise.
     */
    private fun sendCommand(command: String): Boolean =
        try {
            adbConnection?.open("shell:$command")?.close()
            true
        } catch (t: Throwable) {
            L.e(t)
            false
        }

    /** Returns the private key used for ADB TLS client authentication. */
    override fun getPrivateKey(): PrivateKey = privateKey

    /** Returns the self-signed certificate presented during the ADB TLS handshake. */
    override fun getCertificate(): Certificate = certificate

    /** Returns the device name shown in the ADB pairing/authorization dialog. */
    override fun getDeviceName(): String = "Pastelize"
}
