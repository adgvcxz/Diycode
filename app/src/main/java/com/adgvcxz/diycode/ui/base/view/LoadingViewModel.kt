package com.adgvcxz.diycode.ui.base.view

import android.databinding.ObservableBoolean
import com.adgvcxz.diycode.R

/**
 * zhaowei
 * Created by zhaowei on 2017/2/21.
 */

class LoadingViewModel: BaseViewModel() {

    val loading = ObservableBoolean(false)

    override fun contentId(): Int = R.layout.item_loading

}
