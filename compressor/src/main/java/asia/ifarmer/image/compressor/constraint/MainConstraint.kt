package asia.ifarmer.image.compressor.constraint

import android.graphics.Bitmap
import asia.ifarmer.image.compressor.decodeSampledBitmapFromFile
import asia.ifarmer.image.compressor.determineImageRotation
import asia.ifarmer.image.compressor.overWrite
import java.io.File

open class DefaultConstraint(
        private val width: Int = 612,
        private val height: Int = 816,
        private val format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        private val quality: Int = 80
) : Constraint {
    private var isResolved = false

    override fun isSatisfied(imageFile: File): Boolean {
        return isResolved
    }

    override fun satisfy(imageFile: File): File {
        val result = decodeSampledBitmapFromFile(imageFile, width, height).run {
            determineImageRotation(imageFile, this).run {
                overWrite(imageFile, this, format, quality)
            }
        }
        isResolved = true
        return result
    }

}

fun Compress.default(
        width: Int = 612,
        height: Int = 816,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 80
) {
    constraint(DefaultConstraint(width, height, format, quality))
}