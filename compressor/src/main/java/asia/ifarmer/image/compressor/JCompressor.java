package asia.ifarmer.image.compressor;

import android.content.Context;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class JCompressor {
    public static void compressImage(Context context, File imageFile, ImageCompressed imageCompressed) {
        CompletableFuture<File> future = ImageCompressorHelper.INSTANCE.compressImageAsync(context, imageFile);
        future.thenAccept(compressedFile -> {
            if(imageCompressed != null){
                imageCompressed.onImageCompressed(compressedFile);
            }

        }).exceptionally(ex -> null);
    }

    public interface ImageCompressed {
        void onImageCompressed(File compressedFile);
    }
}
