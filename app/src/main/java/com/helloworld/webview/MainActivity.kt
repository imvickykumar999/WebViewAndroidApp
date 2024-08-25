package com.helloworld.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.helloworld.webview.ui.theme.WebViewTheme
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Set the status bar color to navy blue
        window.statusBarColor = android.graphics.Color.parseColor("#031f3d")  // Navy blue hex code

        // Optionally, control status bar icons for light/dark content
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = false  // Set to true if the background is light

        // Set the content to be shown
        setContent {
            WebViewTheme {
                WebViewScreen(url = "https://blogforge.pythonanywhere.com")
            }
        }
    }

    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()  // Go back in WebView's history
        } else {
            super.onBackPressed()  // Exit the app
        }
    }

    @Composable
    fun WebViewScreen(url: String) {
        // AndroidView is used to integrate traditional Android Views in a Composable.
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient() // Ensures links open in the WebView itself
                    settings.javaScriptEnabled = true // Enable JavaScript if needed
                    loadUrl(url)
                    webView = this  // Initialize the WebView reference
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding() // Applies padding based on the status and navigation bars
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun WebViewScreenPreview() {
        WebViewTheme {
            WebViewScreen(url = "https://blogforge.pythonanywhere.com")
        }
    }
}
