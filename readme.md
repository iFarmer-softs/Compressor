# Image Compressor Library for Android

A lightweight and efficient image compression library for Android that supports both **Kotlin** and **Java**. This library helps reduce image file sizes while maintaining quality, making it perfect for optimizing storage and upload speed in Android applications.

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

## Contributing

Feel free to open issues and pull requests if you have improvements or bug fixes! 🙌

---

## Author
[Your Name](https://github.com/yourusername) - Android Developer

---

### ⭐ If you found this library helpful, consider giving it a star on GitHub!

