package com.example.composebitmapcanvassampleproject.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.composebitmapcanvassampleproject.model.Sample
import kotlinx.coroutines.launch

@Composable
fun CanvasDraw(sampleList: List<Sample>) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    Log.e("", "++++++++++++++++screen height = ${configuration.screenHeightDp}")
    Log.e("", "++++++++++++++++screen width = ${configuration.screenWidthDp}")

    // 디바이스 사이즈 이용해서 16 x 9 비율로 width, height 정하기
    val height = configuration.screenWidthDp - 50
    val width = ((height * 16) / 9)

    val bitmapList = mutableListOf<ImageBitmap>()
    LaunchedEffect(true) {
        bitmapList.addAll(createBitmapList(context = context, sampleList = sampleList))
    }

    Canvas(
        modifier = Modifier
            .size(width = width.dp, height = height.dp)
            .background(Color.Red)
    ) {
        bitmapList.forEach { bitmap ->
            drawImage(image = bitmap)
        }
    }
}

suspend fun createBitmapList(context: Context, sampleList: List<Sample>): List<ImageBitmap> {
    val bitmapList = mutableListOf<ImageBitmap>()

    sampleList.forEach { sample ->
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context).data(sample.value).build()

        val bitmap = (loader.execute(request) as SuccessResult).drawable.toBitmap()
        bitmapList.add(bitmap.asImageBitmap())
    }

    return bitmapList
}