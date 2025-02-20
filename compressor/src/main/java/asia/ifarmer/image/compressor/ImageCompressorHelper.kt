package asia.ifarmer.image.compressor

import android.content.Context
import android.graphics.Bitmap
import asia.ifarmer.image.compressor.constraint.Compress
import asia.ifarmer.image.compressor.constraint.DefaultConstraint
import asia.ifarmer.image.compressor.constraint.default
import kotlinx.coroutines.*
import java.io.File
import java.util.concurrent.CompletableFuture

object ImageCompressorHelper {
    fun compressImageAsync(context: Context, imageFile: File): CompletableFuture<File> {
        val future = CompletableFuture<File>()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val result = Compressor.compress(context, imageFile)
                future.complete(result)
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }

    fun compressImageAsync(context: Context, imageFile: File, width: Int): CompletableFuture<File> {
        val future = CompletableFuture<File>()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val result = Compressor.compress(context, imageFile){
                    default(width = width)
                }
                future.complete(result)
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }

    fun compressImageAsync(context: Context, imageFile: File, width: Int, format: Bitmap.CompressFormat): CompletableFuture<File> {
        val future = CompletableFuture<File>()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val result = Compressor.compress(context, imageFile){
                    default(width = width, format = format)
                }
                future.complete(result)
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }

    fun compressImageAsync(context: Context, imageFile: File, format: Bitmap.CompressFormat): CompletableFuture<File> {
        val future = CompletableFuture<File>()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val result = Compressor.compress(context, imageFile){
                    default(format = format)
                }
                future.complete(result)
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }
}