package com.adgvcxz.diycode.util

import io.reactivex.Observable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

fun <T> Observable<T>.takeFirst(t : T): Observable<T> = filter { t == it }.take(1)