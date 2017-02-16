package com.adgvcxz.diycode.ui.base

import com.adgvcxz.diycode.util.FragmentLifeCycleEvent

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

abstract class BaseFragmentViewModel: RxViewModel<FragmentLifeCycleEvent>() {

    abstract fun contentId(): Int

    open fun onCreateView() = lifeCycleNext(FragmentLifeCycleEvent.CreateView)

    open fun onDestroyView() {
        dispose()
        lifeCycleNext(FragmentLifeCycleEvent.DestroyView)
    }
}