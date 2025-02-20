package asia.ifarmer.compressor;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

import asia.ifarmer.image.compressor.JCompressor;

public class Test {
    public static void test(Context context, File file){

        Log.e("file size", file.length() + "");

        JCompressor.compressImage(context, file, new JCompressor.ImageCompressed() {
            @Override
            public void onImageCompressed(File compressedFile) {
                printImageDimensions(compressedFile);
                Log.e("image size fter 2", compressedFile.length() +  "");
            }
        });
    }

    // Define the callback interface to handle completion
    public interface OnCompleteCallback {
        void onComplete(File result);
    }

    public static void printImageDimensions(File imageFile) {
        if (!imageFile.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // Only get dimensions, don't load the bitmap

        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        int width = options.outWidth;
        int height = options.outHeight;
        Log.e("res", width + " " + height);

    }

}
