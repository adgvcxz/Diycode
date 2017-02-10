package com.adgvcxz.diycode.activity

import com.adgvcxz.diycode.databinding.ActivityMainBinding
import com.adgvcxz.diycode.viewmodel.MainActivityViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivity: BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun generateViewModel(): MainActivityViewModel {
        return MainActivityViewModel()
    }
}