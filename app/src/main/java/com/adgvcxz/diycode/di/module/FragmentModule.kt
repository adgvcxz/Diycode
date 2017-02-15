package com.adgvcxz.diycode.di.module

import android.support.v4.app.Fragment
import com.adgvcxz.diycode.di.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * zhaowei
 * Created by zhaowei on 2017/2/15.
 */
@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment = fragment
}
