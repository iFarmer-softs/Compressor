package asia.ifarmer.image.compressor

import android.content.Context
import asia.ifarmer.image.compressor.constraint.default
import kotlinx.coroutines.*
import java.io.File
import java.util.concurrent.CompletableFuture

object ImageCompressorHelper {
    fun compressImageAsync(context: Context, imageFile: File): CompletableFuture<File> {
        val future = CompletableFuture<File>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = Compressor.compress(context, imageFile){
                    default(width = 812)
                }
                future.complete(result)
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }
}