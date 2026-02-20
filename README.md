# 🎨 Pastelize Android

**Welcome, digital minimalist!**

Pastelize is the first and only Android app that lets you adjust screen saturation without root access.

![Pastelize banner](media/banner.png)

<p align="center">
  <a href="https://play.google.com/store/apps/details?id=pastelize.android" target="_blank" rel="noopener noreferrer">
    <img src="media/play-store-button.png" alt="Get Pastelize on Google Play" width="40%" />
  </a>
</p>

## 📖 The Backstory

In 2025, I started the journey to reclaim my attention and focus in this age of algorithms designed to exploit our dopamine system.

On Android, the grayscale filter is strictly binary (ON/OFF). I like it, but sometimes I find myself wishing for just a "hint" of color for better orientation in the UI.

After spending an inappropriate amount of time searching for a way to desaturate the screen without root, I discovered a magical ADB command. Executing it turned my screen into beautiful pastel colors. However, restarting the device resets the beauty and requires a computer to set it back.

So, my next goal was to find a way to execute the command directly on the device. And there was nothing easier than implementing a local wireless ADB connection to the device itself. That was the tricky part, but it works! While the under-the-hood logic is quite complex, I’ve simplified the process as much as possible with a seamless step-by-step guide inside the app.

I thought there might be others who would appreciate a tool like this, and that’s how Pastelize was born.

## 📱 Compatibility and Limitations

Pastelize relies on a system feature available on most devices with Android 15 & 16. However, compatibility varies by brand and is unfortunately outside of my control as a developer.

Due to the nature of this specific implementation, the app has a few minor limitations:

- **Connected Wi-Fi:** Because the app uses the wireless debugging protocol, you must be connected to Wi-Fi to adjust the saturation.
- **Resets:** Saturation resets after a device reboot or if you toggle grayscale via Digital Wellbeing. If you use it, be sure to disable any grayscale mode automations.

## ✒️ E-ink Experience

Premium E-ink simulation modes provide the most eye-friendly experience and the fewest distractions, while supporting the development of the app.

💡 Pro-Tip: Use a matte screen protector for the ultimate paper-like feel.

## 💬 Feedback

Found a bug or have a feature idea? All feedback is welcome!

[Join the Official XDA Developers Thread](https://xdaforums.com/t/app-15-0-pastelize-screen-desaturation-no-root.4779225/)

## 🧡 Support
Like the idea and want to support the project even more? I’d be grateful if you dropped me a few Sats.

```text
bc1qq0elgd8etpkdnnfetja7rqkd2yyt5qfcmhk5u4
```

**Thank you and enjoy the app!**