package com.adgvcxz.diycode.ui.login

import com.adgvcxz.diycode.databinding.ActivityLoginBinding
import com.adgvcxz.diycode.ui.base.BaseActivity

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity<LoginActivityViewModel, ActivityLoginBinding>() {

    override fun initInject() {
        getActivityComponent().inject(this)
    }

//    override fun generateViewModel(): LoginActivityViewModel = LoginActivityViewModel()
}

