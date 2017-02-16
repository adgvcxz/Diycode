package com.adgvcxz.diycode.ui.main.home

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.FragmentHomeBinding
import com.adgvcxz.diycode.ui.base.BaseFragment
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.ui.base.DrawerMenuFragment
import com.adgvcxz.diycode.ui.base.TopicFragment

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>() {

    val fragments = arrayOf(TopicFragment(), DrawerMenuFragment(), TopicFragment())

    override fun inject() {
        fragmentComponent.inject(this)
    }

    override fun initViewAndData(view: View) {
        dataBinding.viewPager.adapter = HomeAdapter(childFragmentManager, fragments,
                activity.resources.getStringArray(R.array.home_tab_titles))
        dataBinding.tabLayout.setupWithViewPager(dataBinding.viewPager)
    }

    class HomeAdapter(fm: FragmentManager,
                      private val fragments: Array<BaseFragment<out BaseFragmentViewModel, ViewDataBinding>>,
                      private val titles: Array<String>): FragmentPagerAdapter(fm) {

        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getPageTitle(position: Int): CharSequence = titles[position]
    }
}
