package asia.ifarmer.compressor;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

import asia.ifarmer.image.compressor.JCompressor;

public class Test {
    public static void test(Context context, File file){

        JCompressor.compressImage(context, file, new JCompressor.ImageCompressed() {
            @Override
            public void onImageCompressed(File compressedFile) {
                printImageDimensions(compressedFile);
            }
        });
    }

    // Define the callback interface to handle completion
    public interface OnCompleteCallback {
        void onComplete(File result);
    }

    public static void printImageDimensions(File imageFile) {
        if (!imageFile.exists()) {
            return;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);


    }

}
