package asia.ifarmer.image.compressor.constraint

import asia.ifarmer.image.compressor.loadBitmap
import asia.ifarmer.image.compressor.overWrite
import java.io.File

class SizeConstraint(
        private val maxFileSize: Long,
        private val stepSize: Int = 10,
        private val maxIteration: Int = 10,
        private val minQuality: Int = 10
) : Constraint {
    private var iteration: Int = 0

    override fun isSatisfied(imageFile: File): Boolean {
        return imageFile.length() <= maxFileSize || iteration >= maxIteration
    }

    override fun satisfy(imageFile: File): File {
        iteration++
        val quality = (100 - iteration * stepSize).takeIf { it >= minQuality } ?: minQuality
        return overWrite(imageFile, loadBitmap(imageFile), quality = quality)
    }
}

fun Compress.size(maxFileSize: Long, stepSize: Int = 10, maxIteration: Int = 10) {
    constraint(SizeConstraint(maxFileSize, stepSize, maxIteration))
}