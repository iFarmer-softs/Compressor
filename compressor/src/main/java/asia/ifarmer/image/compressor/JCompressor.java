package asia.ifarmer.image.compressor;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.core.graphics.BitmapCompat;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class JCompressor {
    public static void compressImage(Context context, File imageFile, ImageCompressed imageCompressed) {
        CompletableFuture<File> future = ImageCompressorHelper.INSTANCE.compressImageAsync(context, imageFile, 200);
        future.thenAccept(compressedFile -> {
            if(imageCompressed != null){
                Log.e("size", "size: " + compressedFile.length());
                imageCompressed.onImageCompressed(compressedFile);
            }

        }).exceptionally(ex -> null);
    }

    public static void compressImage(Context context, File imageFile, int width, ImageCompressed imageCompressed) {
        CompletableFuture<File> future = ImageCompressorHelper.INSTANCE.compressImageAsync(context, imageFile, width);
        future.thenAccept(compressedFile -> {
            if(imageCompressed != null){
                Log.e("size", "size: " + compressedFile.length());
                imageCompressed.onImageCompressed(compressedFile);
            }

        }).exceptionally(ex -> null);
    }

    public static void compressImage(Context context, File imageFile, int width, Bitmap.CompressFormat format, ImageCompressed imageCompressed) {
        CompletableFuture<File> future = ImageCompressorHelper.INSTANCE.compressImageAsync(context, imageFile, width, format);
        future.thenAccept(compressedFile -> {
            if(imageCompressed != null){
                Log.e("size", "size: " + compressedFile.length());
                imageCompressed.onImageCompressed(compressedFile);
            }

        }).exceptionally(ex -> null);
    }

    public static void compressImage(Context context, File imageFile, Bitmap.CompressFormat format, ImageCompressed imageCompressed) {
        CompletableFuture<File> future = ImageCompressorHelper.INSTANCE.compressImageAsync(context, imageFile, format);
        future.thenAccept(compressedFile -> {
            if(imageCompressed != null){
                Log.e("size", "size: " + compressedFile.length());
                imageCompressed.onImageCompressed(compressedFile);
            }

        }).exceptionally(ex -> null);
    }

    public interface ImageCompressed {
        void onImageCompressed(File compressedFile);
    }
}
