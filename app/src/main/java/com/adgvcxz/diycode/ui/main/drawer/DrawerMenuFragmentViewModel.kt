package com.adgvcxz.diycode.ui.main.drawer

import android.app.Activity
import android.content.Intent
import android.view.View
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.ui.login.LoginActivity
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

class DrawerMenuFragmentViewModel @Inject constructor() : BaseFragmentViewModel() {

    @Inject
    lateinit var activity: Activity

    val avatar = ObservableString("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png")
    val placeholder = R.mipmap.ic_launcher
    var name = ObservableString("${System.currentTimeMillis()}")

    fun onClickAvatar(view: View) = view.context.startActivity(Intent(view.context, LoginActivity::class.java))

    override fun contentId(): Int = R.layout.fragment_drawer_menu

}
