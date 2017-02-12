package com.adgvcxz.diycode

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */
class DiyCodeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}
