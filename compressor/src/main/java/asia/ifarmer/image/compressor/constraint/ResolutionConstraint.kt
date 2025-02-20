package asia.ifarmer.image.compressor.constraint

import android.graphics.BitmapFactory
import asia.ifarmer.image.compressor.calculateInSampleSize
import asia.ifarmer.image.compressor.decodeSampledBitmapFromFile
import asia.ifarmer.image.compressor.determineImageRotation
import asia.ifarmer.image.compressor.overWrite
import java.io.File

class ResolutionConstraint(private val width: Int, private val height: Int) : Constraint {

    override fun isSatisfied(imageFile: File): Boolean {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
            calculateInSampleSize(this, width, height) <= 1
        }
    }

    override fun satisfy(imageFile: File): File {
        return decodeSampledBitmapFromFile(imageFile, width, height).run {
            determineImageRotation(imageFile, this).run {
                overWrite(imageFile, this)
            }
        }
    }
}

fun Compress.resolution(width: Int, height: Int) {
    constraint(ResolutionConstraint(width, height))
}