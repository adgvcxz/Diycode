package com.adgvcxz.diycode.ui.main

import android.os.Bundle
import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.ui.base.BaseActivity
import com.adgvcxz.diycode.ui.base.DrawerMenuFragment
import com.adgvcxz.diycode.ui.base.HomeFragment

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(dataBinding.drawerMenuLayout.id, DrawerMenuFragment()).commit()
        supportFragmentManager.beginTransaction().replace(dataBinding.contentLayout.id, HomeFragment()).commit()
    }
}