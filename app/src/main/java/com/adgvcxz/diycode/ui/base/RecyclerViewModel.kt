package com.adgvcxz.diycode.ui.base

import android.databinding.ObservableArrayList
import com.adgvcxz.diycode.observable.ObservableString
import com.adgvcxz.diycode.util.extensions.onAction
import io.reactivex.Observable
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

abstract class RecyclerViewModel<T: BaseViewModel> {

    val items = ObservableArrayList<T>()
    val offset = 0
    val str = ObservableString("abcd")

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
