package com.adgvcxz.diycode.util.extensions

import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.databinding.ObservableList.OnListChangedCallback
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.binding.observable.ResetArrayList
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/24.
 */

fun ObservableBoolean.rx(): Observable<Boolean> {
    return Flowable.create<Boolean>({
        val callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: android.databinding.Observable?, p1: Int) {
                it.onNext(get())
            }
        }
        addOnPropertyChangedCallback(callback)
        it.setCancellable { removeOnPropertyChangedCallback(callback) }
    }, BackpressureStrategy.DROP).toObservable()
}

fun ObservableInt.rx(): Observable<Int> {
    return Flowable.create<Int>({
        val callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: android.databinding.Observable?, p1: Int) {
                it.onNext(get())
            }
        }
        addOnPropertyChangedCallback(callback)
        it.setCancellable { removeOnPropertyChangedCallback(callback) }
    }, BackpressureStrategy.DROP).toObservable()
}

fun ObservableString.rx(): Observable<String> {
    return Flowable.create<String>({
        val callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: android.databinding.Observable?, p1: Int) {
                it.onNext(get())
            }
        }
        addOnPropertyChangedCallback(callback)
        it.setCancellable { removeOnPropertyChangedCallback(callback) }
    }, BackpressureStrategy.DROP).toObservable()
}

fun <T> ResetArrayList<T>.rxChanged(): Observable<Boolean> {
    return Flowable.create<Boolean>({
        val callback = object : OnListChangedCallback<ResetArrayList<T>>() {
            override fun onItemRangeInserted(p0: ResetArrayList<T>?, p1: Int, p2: Int) {
                it.onNext(false)
            }

            override fun onItemRangeChanged(p0: ResetArrayList<T>?, p1: Int, p2: Int) {
                it.onNext(false)
            }

            override fun onItemRangeRemoved(p0: ResetArrayList<T>?, p1: Int, p2: Int) {
                it.onNext(false)
            }

            override fun onItemRangeMoved(p0: ResetArrayList<T>?, p1: Int, p2: Int, p3: Int) {
                it.onNext(false)
            }

            override fun onChanged(p0: ResetArrayList<T>?) {
                it.onNext(true)
            }

        }
        addOnListChangedCallback(callback)
        it.setCancellable { removeOnListChangedCallback(callback) }
    }, BackpressureStrategy.DROP).toObservable()
}