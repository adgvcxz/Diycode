package com.adgvcxz.diycode.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView


/**
 * zhaowei
 * Created by zhaowei on 2017/4/10.
 */

class MarkdownView: WebView {

    private val imageLinkPattern = "\\[(.*)!\\[(.*)\\]\\((.*)\\)(.*)\\]\\((.*)\\)"
    private val imageLinkReplace = "<a href=\"$5\" >$1<img src=\"$3\" />$4</a>"

    private val imagePattern = "!\\[(.*)\\]\\((.*)\\)"
    private val imageReplace = "<img class=\"gcs-img-sign\" src=\"$2\" />"


    var markdownText: String = ""
        set(value) {
            var text = value.replace(imageLinkPattern, imageLinkReplace).replace(imagePattern, imageReplace)
            text = text.replace("\n", "\\\\n").replace("'", "\\\'").replace("\r", "")
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                field = "javascript:preview('$text')"
            } else {
                field = "preview('$text')"
            }
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        if (isInEditMode) {
            return
        }
        val settings = settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        loadUrl("file:///android_asset/html/preview.html")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.allowUniversalAccessFromFileURLs = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                        loadUrl(markdownText)
                    } else {
                        evaluateJavascript(markdownText, null)
                    }
                }
            }
        })
    }
}