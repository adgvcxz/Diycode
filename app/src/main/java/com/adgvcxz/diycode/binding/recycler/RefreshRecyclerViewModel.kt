package com.adgvcxz.diycode.binding.recycler

import android.databinding.ObservableBoolean
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.base.LoadingViewModel
import com.adgvcxz.diycode.util.extensions.rx

/**
 * zhaowei
 * Created by zhaowei on 2017/2/23.
 */
abstract class RefreshRecyclerViewModel<T : BaseViewModel> : RecyclerViewModel<T>() {

    var refresh = ObservableBoolean(false)

    init {
        refresh.rx().filter { it }.subscribe {
            offset = 0
            loadData()
        }
        loadingStatus.rx().filter { offset == 0 && it != LoadingViewModel.Loading }
                .subscribe { refresh.set(false) }
    }
}