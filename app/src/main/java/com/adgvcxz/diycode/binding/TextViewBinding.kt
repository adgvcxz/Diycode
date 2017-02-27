package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.widget.TextView

/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */

@BindingAdapter("markText")
fun TextView.makeDownText(text: String) {
    setText(text)
}
