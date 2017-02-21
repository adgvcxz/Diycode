package com.adgvcxz.diycode.ui.base.view

import com.adgvcxz.diycode.ui.base.view.BaseViewModel
import com.adgvcxz.diycode.util.extensions.httpScheduler
import com.adgvcxz.diycode.util.extensions.takeFirst
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

abstract class RxViewModel<T>: BaseViewModel() {

    private var disposables: CompositeDisposable? = null
    val lifecycleSubject: PublishSubject<T> by lazy { PublishSubject.create<T>() }

    fun addDisposable(disposable: Disposable){
        if (disposables == null) {
            disposables = CompositeDisposable()
        }
        disposables?.add(disposable)
    }

    fun <R> bindUntilEvent(t: T): ObservableTransformer<R, R> {
        return ObservableTransformer {
            it.takeUntil(lifecycleSubject.takeFirst(t = t))
        }
    }

    fun lifeCycleNext(t: T) = lifecycleSubject.onNext(t)

    fun dispose() = disposables?.dispose()

}