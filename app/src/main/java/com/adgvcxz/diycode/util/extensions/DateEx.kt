package com.adgvcxz.diycode.util.extensions

import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/3/2.
 */

fun Date.ago(): String {
    val x = (System.currentTimeMillis() - time) / (1000 * 60)
    when(x) {
        in Int.MIN_VALUE..0 -> return "1 min"
        in 0..60 -> return "$x min"
        in 61..(60 * 24) -> return "${x / 60} h"
        in (60 * 24 + 1)..(60 * 24 * 30) -> return "${x / (60 * 24)} d"
        else -> return "${x / (60 * 24 * 30)} m"
    }
}