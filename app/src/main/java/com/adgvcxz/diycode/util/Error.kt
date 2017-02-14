package com.adgvcxz.diycode.util

import android.util.SparseArray
import java.net.HttpURLConnection

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */

class Error(private var code: Int = Error.Unknown) : Throwable() {

    init {
        if (map[code] == null) {
            code = Error.Unknown
        }
    }

    override val message: String get() = map[code]

    companion object {

        val Unknown = 9999
        val NetWorkNotConnected = 10000
        val EmailNotNull = 10001
        val PasswordNotNull = 10002

        private val map = SparseArray<String>()

        init {
            map.put(HttpURLConnection.HTTP_UNAUTHORIZED, "账号密码错误")
            map.put(Unknown, "未知错误")
            map.put(NetWorkNotConnected, "当前网络不可用")
            map.put(EmailNotNull, "邮箱不能为空")
            map.put(PasswordNotNull, "密码不能为空")
        }
    }
}
