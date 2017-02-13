package com.adgvcxz.diycode.activity

import android.os.Bundle
import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.fragment.BaseFragment
import com.adgvcxz.diycode.viewmodel.DrawerMenuFragmentViewModel
import com.adgvcxz.diycode.viewmodel.HomeFragmentViewModel
import com.adgvcxz.diycode.viewmodel.MainActivityViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(dataBinding.drawerMenuLayout.id, generateFragment(DrawerMenuFragmentViewModel())).commit()
        supportFragmentManager.beginTransaction().replace(dataBinding.contentLayout.id, generateFragment(HomeFragmentViewModel())).commit()
    }

    override fun generateViewModel(): MainActivityViewModel {
        return MainActivityViewModel()
    }
}