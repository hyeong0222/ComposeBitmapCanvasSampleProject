package com.example.composebitmapcanvassampleproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.composebitmapcanvassampleproject.model.Sample

@Composable
fun OverlayLayout(sampleList: List<Sample>) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val canvasHeight = configuration.screenWidthDp
    val canvasWidth = canvasHeight * 16 / 9

    Box(
        modifier = Modifier
        .aspectRatio(16 / 9f)
        .background(Color.White)
    ) {
        sampleList.forEach { sample ->
            val imagePainter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(sample.value).apply {
                    allowHardware(true)
                    size(Size.ORIGINAL)
                }.build()
            )

            val imageWidth = canvasWidth * sample.scaleX
            val imageHeight = canvasHeight * sample.scaleY

            val positionX = canvasWidth * sample.positionX
            val positionY = canvasHeight * sample.positionY

            Image(
                modifier = Modifier
                    .size(width = imageWidth.dp, height = imageHeight.dp)
                    .offset(x = positionX.dp, y = positionY.dp),
                painter = imagePainter,
                contentDescription = ""
            )
        }
    }
}