package com.adgvcxz.diycode.util.extensions

import android.widget.Toast

/**
 * zhaowei
 * Created by zhaowei on 2017/5/9.
 */

fun String.toast() {
    Toast.makeText(app, this, Toast.LENGTH_SHORT).show()
}
