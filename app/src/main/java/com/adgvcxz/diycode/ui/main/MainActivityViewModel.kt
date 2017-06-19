package com.adgvcxz.diycode.ui.main

import android.databinding.ObservableBoolean
import com.adgvcxz.AFViewModel
import com.adgvcxz.IModel
import com.adgvcxz.IMutation
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.main.MainActivityViewModel.Model
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivityViewModel @Inject constructor(private val rxBus: RxBus) : AFViewModel<Model>() {

    override val initModel: Model = Model()

    class Model : IModel {
        val drawerOpen = ObservableBoolean(false)
    }

    enum class Mutation : IMutation {
        CloseDrawer,
        OpenDrawer
    }

    override fun transform(mutation: Observable<IMutation>): Observable<IMutation> {
        val closeDrawer = rxBus.toObservable(OpenMainDrawer::class.java)
                .filter { this.currentModel().drawerOpen.get() }
                .map { Mutation.CloseDrawer }
        val openDrawer = rxBus.toObservable(OpenMainDrawer::class.java)
                .filter { !this.currentModel().drawerOpen.get() }
                .map { Mutation.OpenDrawer }
        return Observable.merge(closeDrawer, openDrawer, mutation)
    }

    override fun scan(model: Model, mutation: IMutation): Model {
        when (mutation) {
            Mutation.CloseDrawer -> model.drawerOpen.set(false)
            Mutation.OpenDrawer -> model.drawerOpen.set(true)
        }
        return model
    }
}
