package com.adgvcxz.diycode.di.component

import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.di.module.AppModule
import com.adgvcxz.diycode.net.ApiService
import dagger.Component
import javax.inject.Singleton

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun getContext(): DiyCodeApp

    fun apiService(): ApiService

    fun inject(app: DiyCodeApp)

}