@file:OptIn(ExperimentalTextApi::class)

package com.example.composebitmapcanvassampleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebitmapcanvassampleproject.ui.CaptureBitmap
import com.example.composebitmapcanvassampleproject.ui.theme.ComposeBitmapCanvasSampleProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBitmapCanvasSampleProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    CaptureBitmap {
        val textMeasurer = rememberTextMeasurer()
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawText(
                    textMeasurer = textMeasurer,
                    text = "Hello this is Sang Woo",
                    topLeft = Offset(10.dp.toPx(), 10.dp.toPx())
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBitmapCanvasSampleProjectTheme {
        Greeting("Android")
    }
}
