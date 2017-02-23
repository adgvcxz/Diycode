package com.adgvcxz.diycode.util.extensions

import android.content.Context
import android.util.TypedValue
import com.adgvcxz.diycode.R

/**
 * zhaowei
 * Created by zhaowei on 2017/2/23.
 */

fun Context.getActionBarHeight(): Int {
    val typedValue = TypedValue()
    if (theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
        return TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
    }
    return resources.getDimensionPixelSize(R.dimen.action_bar_size)
}
