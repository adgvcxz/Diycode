package com.adgvcxz.diycode.binding.recycler

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.base.LoadingViewModel
import com.adgvcxz.diycode.binding.recycler.OnLoadMoreListener
import io.reactivex.Observable
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

abstract class RecyclerViewModel<T : BaseViewModel> {

    val items = ObservableArrayList<T>()
    open var loadMore = ObservableBoolean(false)
    open var loadAll = ObservableBoolean(true)
    open var topMargin = ObservableInt(0)
    var loadingStatus = ObservableInt(LoadingViewModel.Companion.Nothing)
    var offset = 0
    var loadMoreListener = object : OnLoadMoreListener {
        override fun loadMore() {
            loadData()
        }
    }

    fun loadData() {
        request(offset)
                .doOnSubscribe { loadingStatus.set(LoadingViewModel.Loading) }
                .subscribe({
                    if (offset == 0) {
                        items.clear()
                    }
                    items.addAll(it)
                    offset = items.size
                    loadingStatus.set(LoadingViewModel.Nothing)
                }) {
                    loadingStatus.set(LoadingViewModel.LoadFailed)
                }
    }

    abstract fun request(offset: Int): Observable<ArrayList<T>>

}
