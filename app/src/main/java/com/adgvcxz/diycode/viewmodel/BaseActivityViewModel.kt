package com.adgvcxz.diycode.viewmodel

import android.app.Activity
import io.reactivex.disposables.Disposable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivityViewModel {

    protected val disposables = arrayListOf<Disposable>()

    abstract fun contentId(): Int

    fun addDisposable(disposable: Disposable) = disposables.add(disposable)

    open fun restore(model: BaseActivityViewModel) {

    }

    open fun onCreate(activity: Activity) {

    }

    open fun onDestroy(activity: Activity) = disposables
            .filterNot { it.isDisposed }
            .forEach { it.dispose() }
}