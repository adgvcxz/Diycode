package com.adgvcxz.diycode.ui.main

import android.app.Activity
import android.databinding.ObservableBoolean
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.observable.ObservableString
import com.adgvcxz.diycode.rxbus.ClickNavigation
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivityViewModel @Inject constructor() : BaseActivityViewModel() {

    val text = ObservableString("${System.currentTimeMillis()}")

    val drawerOpen = ObservableBoolean(false)

    override fun onCreate(activity: Activity) {
        super.onCreate(activity)
        addDisposable(RxBus.instance.toObservable(ClickNavigation::class.java)
                .map { !drawerOpen.get() }
                .subscribe { open ->
                    drawerOpen.set(open)
                })
    }

    override fun contentId(): Int {
        return R.layout.activity_main
    }

    override fun restore(model: BaseActivityViewModel) {
        if (model is MainActivityViewModel) {
            text.set(model.text.get())
        }
    }
}
