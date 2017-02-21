package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.adgvcxz.diycode.binding.adapter.BaseRecyclerViewAdapter
import com.adgvcxz.diycode.ui.base.view.BaseViewModel
import io.reactivex.Observable
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

fun <T: BaseViewModel> RecyclerView.ensureAdapterNotNull() {
    if (adapter == null) {
        adapter = BaseRecyclerViewAdapter<T>()
    }
}

@BindingAdapter("items")
fun <T : BaseViewModel> RecyclerView.loadData(items: ArrayList<T>?) {
    if (items != null) {
        layoutManager = LinearLayoutManager(context)
        ensureAdapterNotNull<T>()
        (adapter as BaseRecyclerViewAdapter<T>).setList(items)
    }
}

@BindingAdapter(value = *arrayOf("loadMore", "loadAll"), requireAll = false)
fun <T : BaseViewModel> RecyclerView.setLoading(loadMore: Boolean, loadAll: Boolean) {
    ensureAdapterNotNull<T>()
    Log.e("zhaow", " $loadMore  == $loadAll === $this")
    Observable.just(adapter).ofType(BaseRecyclerViewAdapter::class.java)
            .filter { it.loadMore != loadMore || it.loadAll != loadAll }
            .subscribe {
                it.loadMore = loadMore
                it.loadAll = loadAll
            }
}
