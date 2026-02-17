## Pastelize

Welcome, digital minimalists!

![Pastelize banner](media/banner.png)

## 📖 The Backstory

In 2025, I started the journey to reclaim my attention and focus in this age of algorithms designed to exploit our dopamine system.

On Android (at least on Pixel), the grayscale filter is strictly binary (ON/OFF). I like it, but sometimes I find myself wishing for just a "hint" of color for better orientation in the UI or to recognize emojis.

After spending an inappropriate amount of time searching for a way to desaturate the screen without root, I discovered a magical ADB command. Executing it turned my screen into beautiful pastel colors. However, restarting the device resets the beauty and requires a computer to set it back.

So, my next goal was to find a way to execute the command directly on the device. And there was nothing easier than implementing a local wireless ADB connection to the device itself. That was the tricky part, but it works! While the under-the-hood logic is quite complex, I’ve simplified the process as much as possible with a seamless step-by-step guide inside the app.

I thought there might be others who would appreciate a tool like this, and that’s how Pastelize was born.

## 📱 Compatibility Notice and Limitations

Pastelize utilizes a system command that is fully supported on Google Pixel and certain Samsung and OnePlus devices. It might not work on other brands, but I’d be glad if you gave it a try, helping me discover more supported devices.

[See the device compatibility list](compatibility.md)

Due to the nature of this specific implementation, the app has a few minor limitations:

- Connected Wi-Fi: Because the app uses the wireless debugging protocol, you must be connected to Wi-Fi to adjust the saturation.
- Resets: Saturation resets after a device restart or if you toggle grayscale via Digital Wellbeing. If you use it, be sure to disable any grayscale mode automations.

## 🔒 Privacy & Security

I hold myself to high ethical and moral standards. Pastelize executes ONLY the command to set saturation through the ADB protocol and nothing else. To be fully transparent, I will be open-sourcing the core of the app soon.

Pastelize uses Google Analytics solely for device compatibility feedback (manufacturer, model, and Android version) to help me refine the supported devices list.

[Read the privacy policy](privacy_policy.md)

## ✒️ E-ink Experience

Premium E-ink modes provide the most eye-friendly experience and the fewest distractions, while supporting the development of the app. Since I personally hate subscriptions, I decided to make the E-ink Experience a one-time purchase.

💡 Pro-Tip: Use a matte screen protector for the ultimate paper-like feel.

## 💬 Feedback

[Join the Official XDA Developers Thread](https://xdaforums.com/t/app-15-0-pastelize-screen-desaturation-no-root.4779225/)

Device compatibility reports, bug reports, localization errors, and feature requests are highly appreciated.

**Thank you and enjoy the app!**

[Get Pastelize on Google Play](https://play.google.com/store/apps/details?id=pastelize.android)