package com.example.composebitmapcanvassampleproject.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.composebitmapcanvassampleproject.model.Sample
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    val sampleList: List<Sample>
        get() = listOf(
            Sample(
                type = "IMAGE",
                value = "https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4",
                scaleX = 0.2f,
                scaleY = 0.1f,
                positionX = 0.1f,
                positionY = 0.2f,
                sort = 0
            ),
            Sample(
                type = "IMAGE",
                value = "https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4",
                scaleX = 0.2f,
                scaleY = 0.1f,
                positionX = 0.6f,
                positionY = 0.6f,
                sort = 0
            ),
            Sample(
                type = "IMAGE",
                value = "https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4",
                scaleX = 0.2f,
                scaleY = 0.1f,
                positionX = 0.4f,
                positionY = 0.3f,
                sort = 0
            ),
        )

//    private val _bitmapList = MutableStateFlow(listOf<Bitmap>())
//    val bitmapList = _bitmapList.asStateFlow()

    private val _bitmap = MutableSharedFlow<Bitmap>()
    val bitmap = _bitmap.asSharedFlow()

    init {
        generateBitmap()
    }

    private fun generateBitmap() {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            kotlin.runCatching {
                val bitmap = Bitmap.createBitmap(1920, 1080, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
//                canvas.drawColor(Color.WHITE)

                val loader = ImageLoader(context)
                sampleList.forEach { sample ->
                    val request = ImageRequest.Builder(context = context)
                        .data(data = sample.value)
                        .allowHardware(enable = false)
                        .build()

                    val result = loader.execute(request)
                    if (result is SuccessResult) {
                        val resultBitmap = result.drawable.toBitmap()

                        val offsetX = (sample.positionX * 1920).roundToInt()
                        val offsetY = (sample.positionY * 1080).roundToInt()
                        val imageWidth = (sample.scaleX * 1920).roundToInt()
                        val imageHeight = (sample.scaleY * 1080). roundToInt()

                        canvas.drawBitmap(resultBitmap, null, Rect(offsetX, offsetY, offsetX + imageWidth, offsetY + imageHeight), null)
                    }
                }

                _bitmap.emit(bitmap)

//                val path = context.cacheDir.absolutePath + File.separator + "bitmap_test.png"
//                val file = File(path)
//                file.createNewFile()
//                val out = FileOutputStream(file)
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
//                out.close()
//                Log.e("","+++++++++++++++++++++=time = ${System.currentTimeMillis() - startTime}")
            }
        }
    }
}