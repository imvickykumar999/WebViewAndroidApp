package com.helloworld.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.helloworld.webview.ui.theme.WebViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebViewTheme {
                WebViewScreen(url = "https://blogforge.pythonanywhere.com")
            }
        }
    }
}

@Composable
fun WebViewScreen(url: String) {
    // AndroidView is used to integrate traditional Android Views in a Composable.
    androidx.compose.ui.viewinterop.AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient() // Ensures links open in the WebView itself
                settings.javaScriptEnabled = true // Enable JavaScript if needed
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun WebViewScreenPreview() {
    WebViewTheme {
        WebViewScreen(url = "https://logforge.pythonanywhere.com")
    }
}
