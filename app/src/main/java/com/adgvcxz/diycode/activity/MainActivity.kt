package com.adgvcxz.diycode.activity

import android.os.Bundle
import android.util.Log
import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.fragment.BaseFragment
import com.adgvcxz.diycode.viewmodel.DrawerMenuFragmentViewModel
import com.adgvcxz.diycode.viewmodel.MainActivityViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivity: BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("zhaow", "==========")
        supportFragmentManager.beginTransaction().replace(dataBinding.drawerMenuLayout.id, BaseFragment.newInstance(DrawerMenuFragmentViewModel())).commit()
    }

    override fun generateViewModel(): MainActivityViewModel {
        return MainActivityViewModel()
    }
}