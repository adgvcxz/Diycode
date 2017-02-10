package com.adgvcxz.diycode.viewmodel

import android.os.Parcelable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivityViewModel: Parcelable {

    abstract fun contentId(): Int

    open fun restore(model: BaseActivityViewModel) {

    }
}