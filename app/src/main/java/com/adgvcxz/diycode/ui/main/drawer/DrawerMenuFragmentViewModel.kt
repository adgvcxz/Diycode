package com.adgvcxz.diycode.ui.main.drawer

import com.adgvcxz.IModel
import com.adgvcxz.ViewModel
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragmentViewModel.Model
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

class DrawerMenuFragmentViewModel @Inject constructor() : ViewModel<Model>(Model()) {

    class Model : IModel {
        val avatar = ObservableString("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png")
        var name = ObservableString("${System.currentTimeMillis()}")
        val placeholder = R.mipmap.ic_launcher
    }
}
