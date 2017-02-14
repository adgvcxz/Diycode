package com.adgvcxz.diycode.ui.main

import android.os.Bundle
import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.ui.base.BaseActivity
import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragmentViewModel
import com.adgvcxz.diycode.ui.main.home.HomeFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(dataBinding.drawerMenuLayout.id, generateFragment(DrawerMenuFragmentViewModel())).commit()
        supportFragmentManager.beginTransaction().replace(dataBinding.contentLayout.id, generateFragment(HomeFragmentViewModel())).commit()
    }

//    override fun generateViewModel(): MainActivityViewModel {
//        return MainActivityViewModel()
//    }
}