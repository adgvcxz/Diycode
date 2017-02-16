package com.adgvcxz.diycode.ui.main

import android.os.Bundle
import android.util.Log
import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.ui.base.BaseActivity
import com.adgvcxz.diycode.ui.base.DrawerMenuFragment
import com.adgvcxz.diycode.ui.main.home.HomeFragment

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
        Log.e("zhaow", "onCreate")
        supportFragmentManager.beginTransaction().replace(dataBinding.drawerMenuLayout.id, DrawerMenuFragment()).commit()
        supportFragmentManager.beginTransaction().replace(dataBinding.contentLayout.id, HomeFragment()).commit()
    }
}