package asia.ifarmer.compressor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import asia.ifarmer.image.compressor.Compressor
import asia.ifarmer.image.compressor.constraint.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:" + packageName)
                startActivity(intent)
            }

            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data?.data != null) {
            val uri: Uri = data.data!!
            val file = getFileFromUri(this, uri)
            Test.test(this, file)
            GlobalScope.launch(Dispatchers.IO) {
                val f = Compressor.compress(this@MainActivity, file){
                    default(width = 1020)
                }
                printImageDimensions(f)
            }

        }
    }

    fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return File("")
        val file = File(context.cacheDir, "temp_file_${System.currentTimeMillis()}")
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        return file
    }

    fun printImageDimensions(imageFile: File) {
        if (!imageFile.exists()) {
            return
        }

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true // Only get dimensions, don't load the bitmap

        BitmapFactory.decodeFile(imageFile.absolutePath, options)

        val width = options.outWidth
        val height = options.outHeight
    }
}