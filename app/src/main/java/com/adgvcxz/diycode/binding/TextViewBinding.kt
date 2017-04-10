package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.text.Html
import android.widget.TextView
import com.adgvcxz.diycode.util.extensions.ago
import com.adgvcxz.diycode.widget.MarkdownView
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */

@BindingAdapter("markText")
fun TextView.makeDownText(text: String) {
    setText(Html.fromHtml(text))
}

@BindingAdapter("markText")
fun MarkdownView.markDownText(text: String) {
    markdownText = text
}

@BindingAdapter("timeAgo")
fun TextView.setTimeAgo(date: Date) {
    text = date.ago()
}