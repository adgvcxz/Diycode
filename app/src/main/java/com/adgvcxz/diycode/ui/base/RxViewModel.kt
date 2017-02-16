package com.adgvcxz.diycode.ui.base

import com.adgvcxz.diycode.util.extensions.takeFirst
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

open class RxViewModel<in T> {

    private var disposables: CompositeDisposable? = null
    private val lifecycleSubject: PublishSubject<T> by lazy { PublishSubject.create<T>() }

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