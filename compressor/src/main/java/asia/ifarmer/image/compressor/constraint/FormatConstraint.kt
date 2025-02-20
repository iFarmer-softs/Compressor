package asia.ifarmer.image.compressor.constraint

import android.graphics.Bitmap
import asia.ifarmer.image.compressor.compressFormat
import asia.ifarmer.image.compressor.loadBitmap
import asia.ifarmer.image.compressor.overWrite
import java.io.File

class FormatConstraint(private val format: Bitmap.CompressFormat) : Constraint {

    override fun isSatisfied(imageFile: File): Boolean {
        return format == imageFile.compressFormat()
    }

    override fun satisfy(imageFile: File): File {
        return overWrite(imageFile, loadBitmap(imageFile), format)
    }
}

fun Compress.format(format: Bitmap.CompressFormat) {
    constraint(FormatConstraint(format))
}