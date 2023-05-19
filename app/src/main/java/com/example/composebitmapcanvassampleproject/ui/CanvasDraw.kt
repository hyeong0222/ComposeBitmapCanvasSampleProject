package com.example.composebitmapcanvassampleproject.ui

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composebitmapcanvassampleproject.viewmodel.MainViewModel

@Composable
fun CanvasDraw(
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    Log.e("", "++++++++++++++++screen height = ${configuration.screenHeightDp}")
    Log.e("", "++++++++++++++++screen width = ${configuration.screenWidthDp}")

    // 디바이스 사이즈 이용해서 16 x 9 비율로 width, height 정하기 (dp)
    val canvasHeight = configuration.screenWidthDp - 50
    val canvasWidth = ((canvasHeight * 16) / 9)

//    val bitmapList by viewModel.bitmapList.collectAsState()
    val bitmap by viewModel.bitmap.collectAsState(initial = null)

    bitmap?.let {
        Image(
            modifier = Modifier.background(Color.Red),
            bitmap = it.asImageBitmap(),
            contentDescription = "",
        )
    }
//    Canvas(
//        modifier = Modifier
//            .size(width = canvasWidth.dp, height = canvasHeight.dp)
//            .border(2.dp, Color.Black)
//    ) {
//        if (bitmapList.isNotEmpty()) {
//            bitmapList.forEachIndexed { index, bitmap ->
//                val sample = viewModel.sampleList[index]
//                with(density) {
//                    val bitmapWidth = (sample.scaleX * canvasWidth).dp.roundToPx()
//                    val bitmapHeight = (sample.scaleY * canvasHeight).dp.roundToPx()
//                    val resizedBitmap = Bitmap.createScaledBitmap(
//                        bitmap, bitmapWidth, bitmapHeight,false
//                    ).asImageBitmap()
//
//                    val offsetX = canvasWidth.dp.roundToPx() * sample.positionX
//                    val offsetY = canvasHeight.dp.roundToPx() * sample.positionY
//                    drawImage(image = resizedBitmap, topLeft = Offset(offsetX, offsetY))
//                }
//            }
//        }
//    }
}