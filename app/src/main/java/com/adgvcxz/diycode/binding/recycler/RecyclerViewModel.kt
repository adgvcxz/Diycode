package com.adgvcxz.diycode.binding.recycler

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.base.LoadingViewModel
import com.adgvcxz.diycode.binding.observable.ResetArrayList
import com.adgvcxz.diycode.net.ApiService
import io.reactivex.Observable
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

abstract class RecyclerViewModel<T : BaseViewModel> {

    val items = ResetArrayList<T>()
    var loadMore = ObservableBoolean(false)
    val loadAll = ObservableBoolean(true)
    val topMargin = ObservableInt(0)
    val loadingStatus = ObservableInt(LoadingViewModel.Companion.Nothing)
    var offset = 0

    fun loadData() {
        request(offset)
                .doOnSubscribe { loadingStatus.set(LoadingViewModel.Loading) }
                .subscribe({
                    loadingStatus.set(LoadingViewModel.Nothing)
                    if (offset == 0) {
                        items.reset(it)
                    } else {
                        items.addAll(it)
                    }
                    if (it.size < ApiService.Limit) {
                        loadAll.set(true)
                    }
                    offset = items.size
                }) {
                    loadingStatus.set(LoadingViewModel.LoadFailed)
                    it.printStackTrace()
                }
    }

    abstract fun request(offset: Int): Observable<ArrayList<T>>

    open fun onClickItem(t: T) {

    }
}
