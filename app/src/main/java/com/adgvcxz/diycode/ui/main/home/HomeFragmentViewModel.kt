package com.adgvcxz.diycode.ui.main.home

import android.databinding.ObservableArrayList
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/12.
 */

class HomeFragmentViewModel @Inject constructor(private val rxBus: RxBus) : BaseFragmentViewModel() {

    fun openDrawer() = rxBus.post(OpenMainDrawer())

    override fun contentId(): Int = R.layout.fragment_home

}
