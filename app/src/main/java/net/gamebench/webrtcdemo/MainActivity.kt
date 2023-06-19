package net.gamebench.webrtcdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

import com.gamebench.sdk.GameBench


class MainActivity : AppCompatActivity() {

    companion object {
        init {
            GameBench.setUrl(GAMEBENCH_SERVER_URL)
            GameBench.setUser(USERNAME_OR_EMAIL)
            GameBench.setToken(HEX_TOKEN)
            GameBench.setAutoSessionEnabled(true)
            GameBench.setVerboseLoggingEnabled(true)
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.settings.databaseEnabled = true
        webView.settings.domStorageEnabled = true

        webView.loadUrl("https://us-east4.webrtc-test.gbdev.tech/?mode=cloud-game&stream=gfn720p60")
    }
}

