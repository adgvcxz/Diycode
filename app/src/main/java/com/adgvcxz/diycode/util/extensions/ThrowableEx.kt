package com.adgvcxz.diycode.util.extensions

import android.util.Log
import android.widget.Toast
import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.util.Error
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */

fun Throwable.error(): Error {
    when(this) {
        is IOException -> return Error(Error.NetWorkNotConnected)
        is HttpException -> {
            return Error(code())
        }
        !is Error -> return Error(Error.Unknown)
    }
    return this as Error
}

fun Throwable.onAction() {
    Log.w("ErrorAction", error().message)
}

fun Throwable.toast() {
    Toast.makeText(DiyCodeApp.appComponent.getContext(), error().message, Toast.LENGTH_SHORT).show()
    onAction()
}
