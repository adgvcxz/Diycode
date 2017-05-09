package com.adgvcxz.diycode.ui.login

import com.adgvcxz.bindTo
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.ActivityLoginBinding
import com.adgvcxz.diycode.ui.base.BaseActivityNew
import com.adgvcxz.diycode.ui.login.LoginActivityViewModel.Action
import com.adgvcxz.diycode.ui.login.LoginActivityViewModel.Model
import com.jakewharton.rxbinding2.view.clicks

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivityNew<ActivityLoginBinding, LoginActivityViewModel, Model>() {

    override val layoutId: Int = R.layout.activity_login

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initBinding() {
        binding.emailSignInButton.clicks()
                .map { Action.LoginButtonDidClicked }
                .bindTo(viewModel.action)
    }
}

