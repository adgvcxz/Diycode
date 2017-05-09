package com.adgvcxz.diycode.ui.main.home

import com.adgvcxz.IAction
import com.adgvcxz.IModel
import com.adgvcxz.IMutation
import com.adgvcxz.ViewModel
import com.adgvcxz.diycode.rxbus.OpenMainDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.main.home.HomeFragmentViewModel.Model
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/12.
 */

class HomeFragmentViewModel @Inject constructor(private val rxBus: RxBus) : ViewModel<Model>(Model()) {

    class Model: IModel

    enum class Action: IAction {
        toolbarNavigationDidClicked
    }

    override fun mutate(action: IAction): Observable<IMutation> {
        when(action) {
            Action.toolbarNavigationDidClicked -> rxBus.post(OpenMainDrawer())
        }
        return super.mutate(action)
    }
}
