package com.adgvcxz.diycode.util

import android.widget.Toast
import com.adgvcxz.diycode.DiyCodeApp
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */

fun Throwable.error(): Error {
    when(this) {
        is IOException -> return Error(Error.NetWorkNotConnected)
        is HttpException -> return Error(code())
        !is Error -> return Error(Error.Unknown)
    }
    return this as Error
}

fun Throwable.onAction() {
    printStackTrace()
}

fun Throwable.toast() {
    //todo
    error()
}
