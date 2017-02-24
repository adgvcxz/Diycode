package com.adgvcxz.diycode.binding.recycler

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.base.LoadingViewModel
import com.adgvcxz.diycode.binding.observable.ResetArrayList
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
    val loadMoreListener = object : OnLoadMoreListener {
        override fun loadMore() {
            loadData()
        }
    }

    fun loadData() {
        request(offset)
                .doOnSubscribe {
                    if (offset > 0) {
                        loadingStatus.set(LoadingViewModel.Loading)
                    }
                }
                .subscribe({
                    if (offset == 0) {
                        items.reset(it)
                    } else {
                        items.addAll(it)
                        loadingStatus.set(LoadingViewModel.Nothing)
                    }
                    offset = items.size
                }) {
                    if (offset > 0) {
                        loadingStatus.set(LoadingViewModel.LoadFailed)
                    }
                }
    }

    abstract fun request(offset: Int): Observable<ArrayList<T>>

}
