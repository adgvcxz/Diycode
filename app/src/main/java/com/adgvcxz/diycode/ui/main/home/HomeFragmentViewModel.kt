package com.adgvcxz.diycode.ui.main.home

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.util.FragmentLifeCycleEvent
import com.adgvcxz.diycode.util.extensions.httpScheduler
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/12.
 */

class HomeFragmentViewModel @Inject constructor(private val rxBus: RxBus) : BaseFragmentViewModel() {

    fun openDrawer() = rxBus.post(OpenMainDrawer())

    override fun contentId(): Int = R.layout.fragment_home
}
