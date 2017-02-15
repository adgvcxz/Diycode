package com.adgvcxz.diycode.ui.base

import com.adgvcxz.diycode.databinding.FragmentDrawerMenuBinding
import com.adgvcxz.diycode.databinding.FragmentHomeBinding
import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragmentViewModel
import com.adgvcxz.diycode.ui.main.home.HomeFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/15.
 */

class HomeFragment: BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}
class DrawerMenuFragment: BaseFragment<DrawerMenuFragmentViewModel, FragmentDrawerMenuBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}
