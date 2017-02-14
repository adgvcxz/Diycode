package com.adgvcxz.diycode.ui.base

import android.app.Activity
import com.adgvcxz.diycode.util.ActivityLifeCycleEvent
import com.adgvcxz.diycode.util.takeFirst
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivityViewModel {

    protected val disposables = arrayListOf<Disposable>()
    protected val lifecycleSubject = PublishSubject.create<ActivityLifeCycleEvent>()!!

    abstract fun contentId(): Int

    fun addDisposable(disposable: Disposable) = disposables.add(disposable)

    open fun restore(model: BaseActivityViewModel) {

    }

    open fun onCreate(activity: Activity) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE)
    }

    open fun onStop(activity: Activity) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP)
    }

    open fun onDestroy(activity: Activity) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY)
        disposables
                .filterNot { it.isDisposed }
                .forEach { it.dispose() }
    }

    fun <T> bindUntilEvent(): ObservableTransformer<T, T> {
        return ObservableTransformer { sourceObservable ->
            sourceObservable.takeUntil(lifecycleSubject.takeFirst(ActivityLifeCycleEvent.DESTROY))
        }
    }
}