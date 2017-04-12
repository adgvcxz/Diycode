package com.adgvcxz.diycode

import android.app.Application
import com.adgvcxz.diycode.di.component.AppComponent
import com.adgvcxz.diycode.di.component.DaggerAppComponent
import com.adgvcxz.diycode.di.module.AppModule
import com.adgvcxz.diycode.util.AppBlockCanaryContext
import com.github.moduth.blockcanary.BlockCanary
import com.squareup.leakcanary.LeakCanary

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */
class DiyCodeApp : Application() {

    companion object {

        private lateinit var instance: DiyCodeApp

        val appComponent: AppComponent by lazy {
            DaggerAppComponent.builder().appModule(AppModule(instance)).build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LeakCanary.install(this)
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }

}
