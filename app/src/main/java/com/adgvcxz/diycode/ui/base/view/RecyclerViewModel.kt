package com.adgvcxz.diycode.ui.base.view

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.adgvcxz.diycode.util.extensions.onAction
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
    val offset = 0

    fun loadData() {
        request(offset)
                .subscribe({
                    if (offset == 0) {
                        items.clear()
                    }
                    items.addAll(it)
                }, Throwable::onAction)
    }

    abstract fun request(offset: Int): Observable<ArrayList<T>>

}
