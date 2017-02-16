package com.adgvcxz.diycode.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adgvcxz.diycode.databinding.FragmentHomeBinding
import com.adgvcxz.diycode.ui.base.BaseFragment

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class HomeFragment: BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>() {
    override fun inject() {
        Log.e("zhaow", "==========bbbb")
        fragmentComponent.inject(this)
    }

    fun log() = Log.e("zhaow", "hahahah")

    override fun initViewAndData(view: View) {
        dataBinding.tabLayout.setupWithViewPager(dataBinding.viewPager)
    }
}
