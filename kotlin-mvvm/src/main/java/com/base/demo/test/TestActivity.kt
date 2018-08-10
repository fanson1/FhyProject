package com.base.demo.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.webkit.WebChromeClient
import com.base.demo.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        wv.settings.javaScriptEnabled = true
        wv.webChromeClient = WebChromeClient()
        wv.loadUrl("file:///android_asset/dist/index.html#/index")
//        wv.loadUrl("http://wwww.baidu.com")
    }
}
