package com.adgvcxz.diycode.ui.main

import android.databinding.ObservableBoolean
import com.adgvcxz.IMutation
import com.adgvcxz.IState
import com.adgvcxz.ViewModel
import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivityViewModel @Inject constructor() : ViewModel<MainActivityViewModel.State>(State()) {

    class State : IState {
        val drawerOpen = ObservableBoolean(false)
    }

    enum class Mutation : IMutation {
        CloseDrawer,
        OpenDrawer
    }

    override fun transform(mutation: Observable<IMutation>): Observable<IMutation> {
        val closeDrawer = DiyCodeApp.appComponent.getRxBus().toObservable(OpenMainDrawer::class.java)
                .filter { this.currentState.drawerOpen.get() }
                .map { Mutation.CloseDrawer }
        val openDrawer = DiyCodeApp.appComponent.getRxBus().toObservable(OpenMainDrawer::class.java)
                .filter { !this.currentState.drawerOpen.get() }
                .map { Mutation.OpenDrawer }
        return Observable.merge(closeDrawer, openDrawer, mutation)
    }

    override fun scan(state: State, mutation: IMutation): State {
        when (mutation) {
            Mutation.CloseDrawer -> state.drawerOpen.set(false)
            Mutation.OpenDrawer -> state.drawerOpen.set(true)
        }
        return state
    }
}
