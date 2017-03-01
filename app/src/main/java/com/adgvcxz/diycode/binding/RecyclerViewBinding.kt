package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.base.LoadingViewModel
import com.adgvcxz.diycode.binding.recycler.BaseRecyclerViewAdapter
import com.adgvcxz.diycode.binding.recycler.OnLoadMoreListener
import com.adgvcxz.diycode.binding.recycler.OnRecyclerViewItemClickListener
import io.reactivex.Observable
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

fun <T : BaseViewModel> RecyclerView.ensureAdapterNotNull() {
    if (adapter == null) {
        adapter = BaseRecyclerViewAdapter<T>()
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun <T : BaseViewModel> RecyclerView.loadData(items: ArrayList<T>?) {
    if (items != null) {
        if (layoutManager == null) {
            layoutManager = LinearLayoutManager(context)
        }
        ensureAdapterNotNull<T>()
        (adapter as BaseRecyclerViewAdapter<T>).setList(items)
    }
}

@BindingAdapter("loadMore")
fun <T : BaseViewModel> RecyclerView.setLoadMore(loadMore: Boolean) {
    ensureAdapterNotNull<T>()
    Observable.just(adapter).ofType(BaseRecyclerViewAdapter::class.java)
            .filter { it.loadMore != loadMore }
            .subscribe {
                it.loadMore = loadMore
            }
}

@BindingAdapter("loadAll")
fun <T : BaseViewModel> RecyclerView.setLoadAll(loadAll: Boolean) {
    ensureAdapterNotNull<T>()
    Observable.just(adapter).ofType(BaseRecyclerViewAdapter::class.java)
            .filter { it.loadAll != loadAll }
            .subscribe {
                it.loadAll = loadAll
            }
}


@Suppress("UNCHECKED_CAST")
@BindingAdapter("loadMoreListener")
fun <T : BaseViewModel> RecyclerView.setLoadMoreListener(listener: OnLoadMoreListener?) {
    ensureAdapterNotNull<T>()
    if (listener != null) {
        (adapter as BaseRecyclerViewAdapter<T>).loadMoreListener = listener
    }
}

@BindingAdapter("loadingStatus")
fun <T : BaseViewModel> RecyclerView.setLoadSuccess(status: Int) {
    ensureAdapterNotNull<T>()
    Observable.just(adapter).ofType(BaseRecyclerViewAdapter::class.java)
            .filter {
                it.isNotEmpty() && it.loadingModel.status.get() != status
                        && it.loadingModel.status.get() == LoadingViewModel.Loading
            }
            .subscribe { it.loadingModel.status.set(status) }
}

@BindingAdapter("firstTopMargin")
fun <T : BaseViewModel> RecyclerView.setTopMargin(margin: Int) {
    ensureAdapterNotNull<T>()
    Observable.just(adapter).ofType(BaseRecyclerViewAdapter::class.java)
            .filter { it.firstTopMargin != margin }
            .subscribe { it.firstTopMargin = margin }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("onClickItemListener")
fun <T : BaseViewModel> RecyclerView.setOnClickItemListener(listener: OnRecyclerViewItemClickListener<T>) {
    ensureAdapterNotNull<T>()
    Observable.just(adapter).ofType(BaseRecyclerViewAdapter::class.java)
            .subscribe { (it as BaseRecyclerViewAdapter<T>).onClickItemListener = listener }
}