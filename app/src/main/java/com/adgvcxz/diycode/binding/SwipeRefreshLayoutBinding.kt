@file:InverseBindingMethods(
        InverseBindingMethod(type = SwipeRefreshLayout::class, attribute = "refreshing", method = "isRefreshing")
)

package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.databinding.InverseBindingListener
import android.databinding.InverseBindingMethod
import android.databinding.InverseBindingMethods
import android.support.v4.widget.SwipeRefreshLayout

/**
 * zhaowei
 * Created by zhaowei on 2017/2/23.
 */


@BindingAdapter("topOffset")
fun SwipeRefreshLayout.setTopOffset(offset: Int) {
    setProgressViewOffset(false, (offset * 0.5).toInt(), (offset * 1.5).toInt())
}

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.setRefreshing(refreshing: Boolean) {
    if (refreshing != isRefreshing) {
        post { isRefreshing = refreshing }
    }
}

@BindingAdapter("refreshingAttrChanged")
fun SwipeRefreshLayout.setRefreshingListener(refreshingAttrChanged: InverseBindingListener) {
    setOnRefreshListener {
        refreshingAttrChanged.onChange()
    }
}

//@InverseBindingAdapter(attribute = "refreshing")
//fun SwipeRefreshLayout.isRefreshing(): Boolean = isRefreshing





