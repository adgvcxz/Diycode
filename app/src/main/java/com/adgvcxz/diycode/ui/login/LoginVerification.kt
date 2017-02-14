package com.adgvcxz.diycode.ui.login

import android.text.TextUtils
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.bean.Token
import com.adgvcxz.diycode.net.RetrofitHelper
import com.adgvcxz.diycode.util.Error
import com.adgvcxz.diycode.util.Error.Companion.EmailNotNull
import com.adgvcxz.diycode.util.Error.Companion.PasswordNotNull
import com.adgvcxz.diycode.util.httpScheduler
import io.reactivex.Observable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/14.
 */
class LoginVerification {

    companion object {
        fun login(email: String, password: String): Observable<Token> {
            return Observable.just(email).flatMap {
                if (TextUtils.isEmpty(email)) {
                    Observable.error<Error>(Error(EmailNotNull))
                }
                if (TextUtils.isEmpty(password)) {
                    Observable.error<Error>(Error(PasswordNotNull))
                }
                RetrofitHelper.getInstance().getToken(Config.ClientId, Config.ClientSecret, "password", email, password)
                        .httpScheduler()
            }
        }
    }

}