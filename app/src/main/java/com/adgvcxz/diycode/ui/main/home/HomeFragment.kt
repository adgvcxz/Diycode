package com.adgvcxz.diycode.ui.main.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.adgvcxz.bindTo
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.FragmentHomeBinding
import com.adgvcxz.diycode.ui.base.BaseFragmentNew
import com.adgvcxz.diycode.ui.base.NewsFragment
import com.adgvcxz.diycode.ui.base.SitesFragment
import com.adgvcxz.diycode.ui.main.home.HomeFragmentViewModel.Model
import com.adgvcxz.diycode.ui.main.home.topic.TopicFragment
import com.adgvcxz.diycode.util.extensions.stringArr
import com.jakewharton.rxbinding2.support.v7.widget.navigationClicks

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class HomeFragment : BaseFragmentNew<FragmentHomeBinding, HomeFragmentViewModel, Model>() {

    override val layoutId: Int = R.layout.fragment_home

    val fragments: Array<Fragment> = arrayOf(TopicFragment(), NewsFragment(), SitesFragment())

    override fun inject() {
        fragmentComponent.inject(this)
    }


    override fun initBinding() {
        binding.viewPager.offscreenPageLimit = Math.ceil(fragments.size.toDouble() / 2).toInt()
        binding.viewPager.adapter = HomeAdapter(childFragmentManager, fragments, R.array.home_tab_titles.stringArr)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.toolbar.navigationClicks()
                .map { HomeFragmentViewModel.Action.toolbarNavigationDidClicked }
                .bindTo(this.viewModel.action)
    }

    inner class HomeAdapter(fm: FragmentManager,
                      private val fragments: Array<Fragment>,
                      private val titles: Array<String>): FragmentPagerAdapter(fm) {

        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getPageTitle(position: Int): CharSequence = titles[position]
    }
}
