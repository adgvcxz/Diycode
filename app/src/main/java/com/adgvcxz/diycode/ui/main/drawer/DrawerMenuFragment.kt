package com.adgvcxz.diycode.ui.main.drawer

import android.content.Intent
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.FragmentDrawerMenuBinding
import com.adgvcxz.diycode.ui.base.BaseFragmentNew
import com.adgvcxz.diycode.ui.login.LoginActivity

import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragmentViewModel.Model
import com.jakewharton.rxbinding2.view.clicks

/**
 * zhaowei
 * Created by zhaowei on 2017/5/8.
 */

class DrawerMenuFragment: BaseFragmentNew<FragmentDrawerMenuBinding, DrawerMenuFragmentViewModel, Model>() {


    override val layoutId: Int = R.layout.fragment_drawer_menu

    override fun inject() {
        fragmentComponent.inject(this)
    }

    override fun initBinding() {
        binding.avatar.clicks()
                .subscribe { startActivity(Intent(activity, LoginActivity::class.java)) }
    }
}
