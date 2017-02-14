package com.adgvcxz.diycode.ui.login

import android.app.Activity
import android.util.Log
import android.view.View
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.observable.ObservableString
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import com.adgvcxz.diycode.util.httpScheduler
import com.adgvcxz.diycode.util.toast
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class LoginActivityViewModel @Inject constructor() : BaseActivityViewModel() {

    @Inject
    lateinit var apiService: ApiService

    val email = ObservableString("")
    val password = ObservableString("")


    override fun contentId(): Int = R.layout.activity_login

    override fun onCreate(activity: Activity) {
        super.onCreate(activity)

    }

    fun login() {
        verifyLogin().subscribe({

        }, Throwable::toast)
    }
}
