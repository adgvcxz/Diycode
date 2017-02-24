package com.adgvcxz.diycode.binding.recycler

import android.databinding.ObservableBoolean
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.util.extensions.rx
import com.adgvcxz.diycode.util.extensions.rxChanged

/**
 * zhaowei
 * Created by zhaowei on 2017/2/23.
 */
abstract class RefreshRecyclerViewModel<T: BaseViewModel>: RecyclerViewModel<T>() {

    var refresh = ObservableBoolean(false)

    init {
        refresh.rx().filter { it }.subscribe {
            offset = 0
            loadData()
        }
        items.rxChanged().filter { it }.subscribe { refresh.set(!it) }
    }
}