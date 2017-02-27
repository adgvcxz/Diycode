package com.adgvcxz.diycode.ui.main

import android.databinding.ObservableBoolean
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivityViewModel @Inject constructor(private val rxBus: RxBus) : BaseActivityViewModel() {

    val text = ObservableString("${System.currentTimeMillis()}")

    val drawerOpen = ObservableBoolean(false)

    override fun onCreate() {
        super.onCreate()
        addDisposable(rxBus.toObservable(OpenMainDrawer::class.java)
                .map { !drawerOpen.get() }
                .subscribe {
                    drawerOpen.set(it)
                })
    }

    override fun contentId(): Int = R.layout.activity_main
}
