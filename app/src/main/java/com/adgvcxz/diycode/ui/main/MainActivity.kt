package com.adgvcxz.diycode.ui.main

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.ui.base.BaseActivityNew
import com.adgvcxz.diycode.ui.main.MainActivityViewModel.Model
import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragment
import com.adgvcxz.diycode.ui.main.home.HomeFragment

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivity : BaseActivityNew<ActivityMainBinding, MainActivityViewModel, Model>() {

    override fun initInject() {
        activityComponent.inject(this)
    }

    override val layoutId: Int = R.layout.activity_main

    override fun initBinding() {
        supportFragmentManager.beginTransaction().replace(binding.drawerMenuLayout.id, DrawerMenuFragment()).commit()
        supportFragmentManager.beginTransaction().replace(binding.contentLayout.id, HomeFragment()).commit()
    }
}