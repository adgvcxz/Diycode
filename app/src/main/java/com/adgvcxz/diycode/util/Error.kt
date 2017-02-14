package com.adgvcxz.diycode.util

import android.util.SparseArray
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */

class Error(code: Int = Error.Unknown): Throwable() {

    private val code = code

    companion object {

        val Unknown = 9999
        val NetWorkNotConnected = 10000
        val EmailNotNull = 10001
        val PasswordNotNull = 10002

        private val map = SparseArray<String>()

        init {
            map.put(HttpURLConnection.HTTP_NOT_AUTHORITATIVE, "账号密码错误")
            map.put(Unknown, "未知错误")
            map.put(NetWorkNotConnected, "当前网络不可用")
            map.put(EmailNotNull, "邮箱不能为空")
            map.put(PasswordNotNull, "密码不能为空")
        }
    }
}
