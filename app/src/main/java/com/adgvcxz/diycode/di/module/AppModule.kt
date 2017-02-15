package com.adgvcxz.diycode.di.module

import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.net.RetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */
@Module
class AppModule(private val application: DiyCodeApp) {

    @Provides
    @Singleton
    fun provideAppContext(): DiyCodeApp = application

    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetrofitHelper().apiService

}
