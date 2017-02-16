package com.adgvcxz.diycode.ui.base

import android.databinding.ViewDataBinding
import com.adgvcxz.diycode.databinding.FragmentDrawerMenuBinding
import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragmentViewModel
import com.adgvcxz.diycode.ui.main.home.TopicFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/15.
 */


class TopicFragment : BaseFragment<TopicFragmentViewModel, ViewDataBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}

class DrawerMenuFragment : BaseFragment<DrawerMenuFragmentViewModel, FragmentDrawerMenuBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}
