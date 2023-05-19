package com.example.composebitmapcanvassampleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.composebitmapcanvassampleproject.model.Sample
import com.example.composebitmapcanvassampleproject.ui.CanvasDraw
import com.example.composebitmapcanvassampleproject.ui.CaptureBitmap
import com.example.composebitmapcanvassampleproject.ui.OverlayLayout
import com.example.composebitmapcanvassampleproject.ui.theme.ComposeBitmapCanvasSampleProjectTheme
import com.example.composebitmapcanvassampleproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBitmapCanvasSampleProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
//                    OverlayLayout(sampleList = viewModel.sampleList)
                    CanvasDraw()
//                    CaptureBitmap {
////                        val textMeasurer = rememberTextMeasurer()
////                        Canvas(
////                            modifier = Modifier.fillMaxSize(),
////                            onDraw = {
////                                drawText(
////                                    textMeasurer = textMeasurer,
////                                    text = "Hello world!",
////                                    topLeft = Offset(100.dp.toPx(), 100.dp.toPx()),
////                                )
////
////                                drawImage(
////
////                                )
////                            }
////                        )
//                        val url =
//                            "https://avatars3.githubusercontent.com/u/35650605?s=400&u=058086fd5c263f50f2fbe98ed24b5fbb7d437a4e&v=4"
//                        val painter = rememberAsyncImagePainter(model = url)
//                        Canvas(modifier = Modifier.size(10.dp).background(Color.)) {
////                            drawImage(image = with(painter) { draw(size) },  )
////                            with(painter) {
////                                draw(size = size)
////                            }
////                            drawIntoCanvas {  }
////                            drawText()
////                            drawImage()
//                        }
//                    }

                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    CaptureBitmap {
//        val textMeasurer = rememberTextMeasurer()
//        Canvas(
//            modifier = Modifier.fillMaxSize(),
//            onDraw = {
//                drawText(
//                    textMeasurer = textMeasurer,
//                    text = "Hello this is Sang Woo",
//                    topLeft = Offset(10.dp.toPx(), 10.dp.toPx())
//                )
//            }
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeBitmapCanvasSampleProjectTheme {
//        Greeting("Android")
//    }
//}
