package com.adgvcxz.diycode.util.extensions

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

/**
 * zhaowei
 * Created by zhaowei on 2017/3/2.
 */

val Int.string: String get() = app.getString(this)
val Int.stringArr: Array<String> get() = app.resources.getStringArray(this)
fun Int.drawable(): Drawable = ContextCompat.getDrawable(app, this)
