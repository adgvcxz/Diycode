package com.adgvcxz.diycode.viewmodel

import android.app.Activity
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.net.RetrofitHelper

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class LoginActivityViewModel : BaseActivityViewModel() {


    override fun contentId(): Int = R.layout.activity_login

    override fun onCreate(activity: Activity) {
        super.onCreate(activity)
        RetrofitHelper.getInstance().getToken(Config.ClientId, Config.ClientSecret, "a", "aaa", "111")

    }
}
