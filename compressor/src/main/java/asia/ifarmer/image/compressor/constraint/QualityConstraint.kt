package asia.ifarmer.image.compressor.constraint

import asia.ifarmer.image.compressor.loadBitmap
import asia.ifarmer.image.compressor.overWrite
import java.io.File

class QualityConstraint(private val quality: Int) : Constraint {
    private var isResolved = false

    override fun isSatisfied(imageFile: File): Boolean {
        return isResolved
    }

    override fun satisfy(imageFile: File): File {
        val result = overWrite(imageFile, loadBitmap(imageFile), quality = quality)
        isResolved = true
        return result
    }
}

fun Compress.quality(quality: Int) {
    constraint(QualityConstraint(quality))
}

