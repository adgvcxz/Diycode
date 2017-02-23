package com.adgvcxz.diycode

import android.app.Application
import com.adgvcxz.diycode.di.component.AppComponent
import com.adgvcxz.diycode.di.component.DaggerAppComponent
import com.adgvcxz.diycode.di.module.AppModule
import com.adgvcxz.diycode.net.initX509
import com.adgvcxz.diycode.net.setRetry
import com.adgvcxz.diycode.util.AppBlockCanaryContext
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.github.moduth.blockcanary.BlockCanary
import com.squareup.leakcanary.LeakCanary
import okhttp3.OkHttpClient

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
        initFresco()
        LeakCanary.install(this)
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }

    private fun initFresco() {
        val builder = OkHttpClient.Builder()
        builder.initX509()
        builder.setRetry()
        val config = OkHttpImagePipelineConfigFactory.newBuilder(this, builder.build()).setDownsampleEnabled(true).build()
        Fresco.initialize(this, config)
    }
}
