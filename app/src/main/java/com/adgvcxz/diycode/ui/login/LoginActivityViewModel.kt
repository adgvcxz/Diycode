package com.adgvcxz.diycode.ui.login

import android.app.Activity
import android.util.Log
import android.view.View
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.net.RetrofitHelper
import com.adgvcxz.diycode.observable.ObservableString
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class LoginActivityViewModel : BaseActivityViewModel() {

    val email = ObservableString("")
    val password = ObservableString("")


    override fun contentId(): Int = R.layout.activity_login

    override fun onCreate(activity: Activity) {
        super.onCreate(activity)

    }

    fun login(view: View) {
        LoginVerification.login(email.get(), password.get())
                .subscribe({

                }, {

                })
    }
}
