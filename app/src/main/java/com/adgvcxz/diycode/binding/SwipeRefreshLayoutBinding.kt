@file:InverseBindingMethods(
        InverseBindingMethod(type = SwipeRefreshLayout::class, attribute = "refreshing", method = "isRefreshing")
)

@file:BindingMethods(
        BindingMethod(type = SwipeRefreshLayout::class, attribute = "refreshing", method = "setRefreshing")
)

package com.adgvcxz.diycode.binding

import android.databinding.*
import android.support.v4.widget.SwipeRefreshLayout
import io.reactivex.Observable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/23.
 */


@BindingAdapter("topOffset")
fun SwipeRefreshLayout.setTopOffset(offset: Int) {
    Observable.just(offset).filter { offset > 0 }
            .subscribe { setProgressViewOffset(false, (it * 0.5).toInt(), (it * 1.5).toInt()) }
}

@BindingAdapter("refreshingAttrChanged")
fun SwipeRefreshLayout.setRefreshingListener(refreshingAttrChanged: InverseBindingListener) {
    setOnRefreshListener {
        refreshingAttrChanged.onChange()
    }
}

//@InverseBindingAdapter(attribute = "refreshing")
//fun SwipeRefreshLayout.isRefreshing(): Boolean = isRefreshing





