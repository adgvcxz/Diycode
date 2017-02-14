package com.adgvcxz.diycode.di.module

import android.app.Activity
import com.adgvcxz.diycode.di.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity = activity


}