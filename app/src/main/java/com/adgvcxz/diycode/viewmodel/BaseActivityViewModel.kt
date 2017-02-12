package com.adgvcxz.diycode.viewmodel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivityViewModel {

    abstract fun contentId(): Int

    open fun restore(model: BaseActivityViewModel) {

    }
}