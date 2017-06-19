package com.adgvcxz.diycode.ui.main.home

import com.adgvcxz.*
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.main.home.HomeFragmentViewModel.Model
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/12.
 */

class HomeFragmentViewModel @Inject constructor(private val rxBus: RxBus) : AFViewModel<Model>() {

    override val initModel: Model = Model()

    class Model: IModel

    enum class Action: IEvent {
        toolbarNavigationDidClicked
    }

    override fun mutate(event: IEvent): Observable<IMutation> {
        when(event) {
            Action.toolbarNavigationDidClicked -> rxBus.post(OpenMainDrawer())
        }
        return super.mutate(event)
    }
}
