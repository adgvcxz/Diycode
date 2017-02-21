package com.adgvcxz.diycode.ui.base

import com.adgvcxz.diycode.ui.base.view.RxViewModel
import com.adgvcxz.diycode.util.ActivityLifeCycleEvent

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivityViewModel: RxViewModel<ActivityLifeCycleEvent>() {

    open fun onCreate() {
        lifeCycleNext(ActivityLifeCycleEvent.Create)
    }

    open fun onStop() {
        lifeCycleNext(ActivityLifeCycleEvent.Stop)
    }

    open fun onDestroy() {
        lifeCycleNext(ActivityLifeCycleEvent.Destroy)
        dispose()
    }

}