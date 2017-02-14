package com.adgvcxz.diycode.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

fun <T> Observable<T>.takeFirst(t: T): Observable<T> = filter { t == it }.take(1)

fun <T> Observable<T>.takeFirst(predicate: Predicate<in T>) = filter(predicate).take(1)

fun <T> Observable<T>.httpScheduler(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

