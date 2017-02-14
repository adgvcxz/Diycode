package com.adgvcxz.diycode

import android.app.Application
import com.adgvcxz.diycode.di.component.AppComponent
import com.adgvcxz.diycode.di.component.DaggerAppComponent
import com.adgvcxz.diycode.di.module.AppModule
import com.adgvcxz.diycode.net.initX509
import com.adgvcxz.diycode.net.setRetry
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */
class DiyCodeApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initFresco()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    private fun initFresco() {
        val builder = OkHttpClient.Builder()
        builder.initX509()
        builder.setRetry()
        val config = OkHttpImagePipelineConfigFactory.newBuilder(this, builder.build()).setDownsampleEnabled(true).build()
        Fresco.initialize(this, config)
    }
}
