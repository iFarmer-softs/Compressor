package asia.ifarmer.image.compressor

import android.content.Context
import asia.ifarmer.image.compressor.constraint.Compress
import asia.ifarmer.image.compressor.constraint.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.CoroutineContext

 object Compressor {
    suspend fun compress(
        context: Context,
        imageFile: File,
        coroutineContext: CoroutineContext = Dispatchers.IO,
        compressPatch: Compress.() -> Unit = { default() }
    ) = withContext(coroutineContext) {
        val compress = Compress().apply(compressPatch)
        var result = copyToCache(context, imageFile)
        compress.constraints.forEach { constraint ->
            while (constraint.isSatisfied(result).not()) {
                result = constraint.satisfy(result)
            }
        }
        return@withContext result
    }
}