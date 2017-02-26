package com.adgvcxz.diycode.ui.base

import android.databinding.ViewDataBinding
import com.adgvcxz.diycode.databinding.FragmentDrawerMenuBinding
import com.adgvcxz.diycode.ui.main.drawer.DrawerMenuFragmentViewModel
import com.adgvcxz.diycode.ui.main.home.news.NewsFragmentViewModel
import com.adgvcxz.diycode.ui.main.home.sites.SitesFragmentViewModel
import com.adgvcxz.diycode.ui.main.home.topic.TopicFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/15.
 */


class DrawerMenuFragment : BaseFragment<DrawerMenuFragmentViewModel, FragmentDrawerMenuBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}

class TopicFragment : BaseFragment<TopicFragmentViewModel, ViewDataBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}

class NewsFragment: BaseFragment<NewsFragmentViewModel, ViewDataBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }
}

class SitesFragment: BaseFragment<SitesFragmentViewModel, ViewDataBinding>() {
    override fun inject() {
        fragmentComponent.inject(this)
    }

}

