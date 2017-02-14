package com.adgvcxz.diycode.ui.login

import com.adgvcxz.diycode.databinding.ActivityLoginBinding
import com.adgvcxz.diycode.ui.base.BaseActivity
import com.adgvcxz.diycode.ui.login.LoginActivityViewModel

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity<LoginActivityViewModel, ActivityLoginBinding>() {

    override fun generateViewModel(): LoginActivityViewModel = LoginActivityViewModel()
}

