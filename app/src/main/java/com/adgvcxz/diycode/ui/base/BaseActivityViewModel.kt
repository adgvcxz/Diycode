package com.adgvcxz.diycode.ui.base

import com.adgvcxz.diycode.binding.base.RxViewModel
import com.adgvcxz.diycode.util.ActivityLifeCycleEvent
import com.adgvcxz.diycode.util.extensions.takeFirst
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

    fun <T> httpScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .takeUntil(lifecycleSubject.takeFirst(ActivityLifeCycleEvent.Destroy))
        }
    }

}