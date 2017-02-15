package com.adgvcxz.diycode.ui.main

import android.app.Activity
import android.databinding.ObservableBoolean
import android.util.Log
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

    @Inject
    lateinit var rxBus: RxBus

    @Inject
    lateinit var activity: Activity

    val text = ObservableString("${System.currentTimeMillis()}")

    val drawerOpen = ObservableBoolean(false)

    override fun onCreate(activity: Activity) {
        super.onCreate(activity)
        addDisposable(rxBus.toObservable(ClickNavigation::class.java)
                .map { !drawerOpen.get() }
                .subscribe {
                    drawerOpen.set(it)
                })
    }

    override fun contentId(): Int {
        Log.e("zhaow11", activity.toString())
        return R.layout.activity_main
    }

    override fun restore(model: BaseActivityViewModel) {
        if (model is MainActivityViewModel) {
            text.set(model.text.get())
        }
    }
}
