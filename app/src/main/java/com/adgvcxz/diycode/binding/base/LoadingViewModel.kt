package com.adgvcxz.diycode.binding.base

import android.databinding.ObservableInt
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/21.
 */

open class LoadingViewModel: BaseViewModel() {

    companion object {

        val Loading = 1

        @JvmStatic
        val LoadFailed = 2

        val Nothing = 3
    }

    val status = ObservableInt(Nothing)

    override fun contentId(): Int = R.layout.item_loading

    fun loadMore() {
        status.set(Loading)
    }

}
