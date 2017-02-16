package com.adgvcxz.diycode.ui.login

import android.text.TextUtils
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.bean.Token
import com.adgvcxz.diycode.util.ActivityLifeCycleEvent
import com.adgvcxz.diycode.util.Error
import com.adgvcxz.diycode.util.Error.Companion.EmailNotNull
import com.adgvcxz.diycode.util.Error.Companion.PasswordNotNull
import com.adgvcxz.diycode.util.extensions.httpScheduler
import io.reactivex.Observable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */
fun LoginActivityViewModel.verifyLogin(): Observable<Token> {

    return Observable.just(email.get()).flatMap {
        if (TextUtils.isEmpty(email.get())) {
            throw Error(EmailNotNull)
        }
        if (TextUtils.isEmpty(password.get())) {
            throw Error(PasswordNotNull)
        }
        apiService.getToken(Config.ClientId, Config.ClientSecret, Config.GrantType, email.get(), password.get())
                .compose(bindUntilEvent<Token>(ActivityLifeCycleEvent.Destroy))
                .httpScheduler()
    }

}