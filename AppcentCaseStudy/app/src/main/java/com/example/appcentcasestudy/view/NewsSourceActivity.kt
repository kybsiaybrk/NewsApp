package com.example.appcentcasestudy.view

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.appcentcasestudy.R

class NewsSourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_source)


        //widgetlar
        val buttonBack = findViewById<Button>(R.id.backButton)
        val webView: WebView = findViewById(R.id.webView)


        //newsdetailactivityden gelen url bilgisini içeren intenti alan kod
        val url = intent.getStringExtra("url")


        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()


        //urlin head kısmı
        webView.loadUrl(url ?: "https://example.com")


        //newsdetailactivty'e döner
        buttonBack.setOnClickListener {
            val intent = Intent(this, NewsDetailActivity::class.java)
            startActivity(intent)
        }

    }


}

