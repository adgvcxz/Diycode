package com.adgvcxz.diycode.net

/**
 * zhaowei
 * Created by zhaowei on 2017/5/9.
 */

enum class ApiError(val message: String) {
    EmailPasswordError("账号密码错误"),
    EmailNullError("请输入邮箱"),
    PasswordNullError("请输入密码"),
}

val Throwable.apiError: ApiError
    get() = ApiError.EmailPasswordError