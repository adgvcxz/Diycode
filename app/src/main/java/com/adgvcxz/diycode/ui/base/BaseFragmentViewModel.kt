package com.adgvcxz.diycode.ui.base

import android.app.Activity
import com.adgvcxz.diycode.binding.base.RxViewModel
import com.adgvcxz.diycode.util.FragmentLifeCycleEvent
import com.adgvcxz.diycode.util.extensions.httpScheduler
import com.adgvcxz.diycode.util.extensions.takeFirst
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

abstract class BaseFragmentViewModel: RxViewModel<FragmentLifeCycleEvent>() {

    open fun onCreateView() = lifeCycleNext(FragmentLifeCycleEvent.CreateView)

    open fun onDestroyView() {
        dispose()
        lifeCycleNext(FragmentLifeCycleEvent.DestroyView)
    }

    fun <T> httpScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .takeUntil(lifecycleSubject.takeFirst(FragmentLifeCycleEvent.DestroyView))
        }
    }
}