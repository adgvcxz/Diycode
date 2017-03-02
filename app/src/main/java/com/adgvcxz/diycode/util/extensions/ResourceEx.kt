package com.adgvcxz.diycode.util.extensions

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

/**
 * zhaowei
 * Created by zhaowei on 2017/3/2.
 */

fun Int.string(): String = getContext().getString(this)
fun Int.stringArr(): Array<String> = getContext().resources.getStringArray(this)
fun Int.drawable(): Drawable = ContextCompat.getDrawable(getContext(), this)
