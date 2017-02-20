package com.adgvcxz.diycode.di.component

import android.support.v4.app.Fragment
import com.adgvcxz.diycode.di.FragmentScope
import com.adgvcxz.diycode.di.module.FragmentModule
import com.adgvcxz.diycode.ui.base.DrawerMenuFragment
import com.adgvcxz.diycode.ui.base.NewsFragment
import com.adgvcxz.diycode.ui.base.TopicFragment
import com.adgvcxz.diycode.ui.main.home.HomeFragment
import dagger.Component

/**
 * zhaowei
 * Created by zhaowei on 2017/2/15.
 */
@FragmentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun getFragment(): Fragment

    fun inject(topicFragment: TopicFragment)

    fun inject(newsFragment: NewsFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(drawerMenuFragment: DrawerMenuFragment)


}