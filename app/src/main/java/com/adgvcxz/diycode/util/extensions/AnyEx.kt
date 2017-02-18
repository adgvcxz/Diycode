package com.adgvcxz.diycode.util.extensions

import android.os.Looper

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

fun ensureChangeOnMainThread() {
    if (Thread.currentThread() != Looper.getMainLooper().thread) {
        throw IllegalStateException("You must only modify the ObservableList on the main thread.")
    }
}
