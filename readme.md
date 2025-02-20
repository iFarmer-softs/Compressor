# Image Compressor Library for Android

A lightweight and efficient image compression library for Android that supports both **Kotlin** and **Java**. This library helps reduce image file sizes while maintaining quality, making it perfect for optimizing storage and upload speed in Android applications.

This project is extended from [zetbaitsu/Compressor](https://github.com/zetbaitsu/Compressor) with additional features and improvements.

## Features
- Compress images with custom width and format.
- Asynchronous image compression using **Kotlin Coroutines**.
- Java-compatible API with callback-based compression.
- Supports multiple image formats (JPEG, PNG, WEBP).
- Easily configurable compression settings.

---

## Installation

### Gradle
Add the following dependency in your `build.gradle` (Module) file:

```gradle
dependencies {
    implementation 'com.github.yourusername:image-compressor:1.0.0'
}
```

*(Replace `yourusername` with your actual GitHub username if using JitPack.)*

### Maven
If you are using Maven, add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>com.github.yourusername</groupId>
    <artifactId>image-compressor</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## Usage

### **Kotlin**
```kotlin
GlobalScope.launch(Dispatchers.IO) {
    val compressedFile = Compressor.compress(this@MainActivity, file) {
        default(width = 1020)
    }
    printImageDimensions(compressedFile)
}
```

### **Java**
```java
JCompressor.compressImage(context, file, new JCompressor.ImageCompressed() {
    @Override
    public void onImageCompressed(File compressedFile) {
        printImageDimensions(compressedFile);
    }
});
```

#### **Custom Compression Settings in Java**
```java
JCompressor.compressImage(context, file, 1200, new JCompressor.ImageCompressed() {
    @Override
    public void onImageCompressed(File compressedFile) {
        printImageDimensions(compressedFile);
    }
});

JCompressor.compressImage(context, file, 1200, Bitmap.CompressFormat.WEBP, new JCompressor.ImageCompressed() {
    @Override
    public void onImageCompressed(File compressedFile) {
        printImageDimensions(compressedFile);
    }
});

JCompressor.compressImage(context, file, Bitmap.CompressFormat.WEBP, new JCompressor.ImageCompressed() {
    @Override
    public void onImageCompressed(File compressedFile) {
        printImageDimensions(compressedFile);
    }
});
```

---

## API Reference

### **Kotlin API**
```kotlin
suspend fun Compressor.compress(
    context: Context,
    imageFile: File,
    coroutineContext: CoroutineContext = Dispatchers.IO,
    compressConfig: Compress.() -> Unit = { default() }
): File
```

### **Java API**
```java
public class JCompressor {
    public static void compressImage(
        Context context,
        File file,
        ImageCompressed callback
    )
    
    public static void compressImage(
        Context context,
        File file,
        int width,
        ImageCompressed callback
    )
    
    public static void compressImage(
        Context context,
        File file,
        int width,
        Bitmap.CompressFormat format,
        ImageCompressed callback
    )

    public interface ImageCompressed {
        void onImageCompressed(File compressedFile);
    }
}
```

---

## License

```
MIT License

Copyright (c) 2025 Your Name

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## Contributing

Feel free to open issues and pull requests if you have improvements or bug fixes! 🙌

---

## Author
[ifarmer](https://github.com/iFarmer-softs)

---

### ⭐ If you found this library helpful, consider giving it a star on GitHub!

