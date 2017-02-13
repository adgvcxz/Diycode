package com.adgvcxz.diycode.rxbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class RxBus private constructor() {

    private val bus: Subject<Any> by lazy {
        PublishSubject.create<Any>().toSerialized()
    }

    private object Holder {
        val Instance = RxBus()
    }

    companion object {
        val instance: RxBus by lazy {
            Holder.Instance
        }
    }

    fun post(event: Any) = bus.onNext(event)

    fun <T> toObservable(event: Class<T>): Observable<T> = bus.ofType(event)
}
