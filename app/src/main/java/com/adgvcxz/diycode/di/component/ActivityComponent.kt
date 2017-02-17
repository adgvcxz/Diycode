package com.adgvcxz.diycode.di.component

import android.app.Activity
import com.adgvcxz.diycode.di.ActivityScope
import com.adgvcxz.diycode.di.module.ActivityModule
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.login.LoginActivity
import com.adgvcxz.diycode.ui.main.MainActivity
import dagger.Component

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity(): Activity

    fun getRxBus(): RxBus

    fun getApiService(): ApiService

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)

}
