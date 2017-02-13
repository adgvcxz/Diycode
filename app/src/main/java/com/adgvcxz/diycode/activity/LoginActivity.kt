package com.adgvcxz.diycode.activity

import com.adgvcxz.diycode.databinding.ActivityLoginBinding
import com.adgvcxz.diycode.viewmodel.LoginActivityViewModel

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity<LoginActivityViewModel, ActivityLoginBinding>() {

    override fun generateViewModel(): LoginActivityViewModel = LoginActivityViewModel()
}

