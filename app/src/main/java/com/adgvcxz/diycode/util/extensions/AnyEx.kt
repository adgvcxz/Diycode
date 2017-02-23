package com.adgvcxz.diycode.util.extensions

import android.content.Context
import android.os.Looper
import com.adgvcxz.diycode.DiyCodeApp

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

fun ensureChangeOnMainThread() {
    if (Thread.currentThread() != Looper.getMainLooper().thread) {
        throw IllegalStateException("You must only modify the ObservableList on the main thread.")
    }
}

fun getContext(): Context = DiyCodeApp.appComponent.getContext()
