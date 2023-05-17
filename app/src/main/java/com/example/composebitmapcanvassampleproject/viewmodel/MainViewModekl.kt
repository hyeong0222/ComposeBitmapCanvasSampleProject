package com.example.composebitmapcanvassampleproject.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.composebitmapcanvassampleproject.model.Sample
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    val sampleList: List<Sample>
        get() = listOf(
            Sample(
                type = "IMAGE",
                value = "https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4",
                scaleX = 0.03f,
                scaleY = 0.01f,
                positionX = 0f,
                positionY = 0f,
                sort = 0
            ),
            Sample(
                type = "IMAGE",
                value = "https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4",
                scaleX = 0.1f,
                scaleY = 0.1f,
                positionX = 0.2f,
                positionY = 0.2f,
                sort = 0
            ),
        )

    private val _bitmapList = MutableStateFlow(listOf<Bitmap>())
    val bitmapList = _bitmapList.asStateFlow()

    init {
        createBitmapList(sampleList = sampleList)
    }

    private fun createBitmapList(sampleList: List<Sample>) {
        viewModelScope.launch {
            kotlin.runCatching {
                val bitmapList = mutableListOf<Bitmap>()

                val loader = ImageLoader(context)

                sampleList.forEach { sample ->
                    val request = ImageRequest.Builder(context = context)
                        .data(data = sample.value)
                        .allowHardware(enable = false)
                        .build()

                    val result = loader.execute(request)
                    if (result is SuccessResult) {
                        bitmapList.add(result.drawable.toBitmap())
                    }
                }

                _bitmapList.value = bitmapList
            }
        }
    }

    fun drawCanvas() {
        viewModelScope.launch {
            kotlin.runCatching {
                val loader = ImageLoader(context)
                val request = ImageRequest.Builder(context)
                    .data("https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4")
                    .allowHardware(false) // Disable hardware bitmaps.
                    .build()

//            val portraitBitmap = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888)
//            val portraitCanvas = Canvas(portraitBitmap)
//            portraitCanvas.drawColor(Color.WHITE)
//
                val landscapeBitmap = Bitmap.createBitmap(1920, 1080, Bitmap.Config.ARGB_8888)
                val landscapeCanvas = Canvas(landscapeBitmap)
//            landscapeCanvas.drawColor(Color.WHITE)
                val result = loader.execute(request)
                if (result is SuccessResult) {
                    val profile = (result.drawable as BitmapDrawable).bitmap
                    val portraitOffsetY = 238
                    val landscapeOffsetY = 88
                    val imageSize = 478
//                    portraitCanvas.drawBitmap(
//                        profile,
//                        null,
//                        Rect(
//                            (1080 - imageSize) / 2,
//                            (1920 - imageSize) / 2 - portraitOffsetY,
//                            (1080 - imageSize) / 2 + imageSize,
//                            (1920 - imageSize) / 2 - portraitOffsetY + imageSize
//                        ),
//                        null
//                    )
                    landscapeCanvas.drawBitmap(
                        profile,
                        null,
                        Rect(
                            (1920 - imageSize) / 2,
                            (1080 - imageSize) / 2 - landscapeOffsetY,
                            (1920 - imageSize) / 2 + imageSize,
                            (1080 - imageSize) / 2 - landscapeOffsetY + imageSize
                        ),
                        null
                    )
                }
            }
        }
    }
}