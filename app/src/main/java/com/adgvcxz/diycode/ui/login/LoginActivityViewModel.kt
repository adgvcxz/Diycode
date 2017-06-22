package com.adgvcxz.diycode.ui.login

import android.databinding.ObservableBoolean
import android.util.Log
import com.adgvcxz.AFViewModel
import com.adgvcxz.IEvent
import com.adgvcxz.IModel
import com.adgvcxz.IMutation
import com.adgvcxz.diycode.bean.Token
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.net.apiError
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.rxbus.RxBusShowToast
import com.adgvcxz.diycode.ui.login.LoginActivityViewModel.Model
import com.adgvcxz.diycode.util.extensions.httpScheduler
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class LoginActivityViewModel @Inject constructor(private val api: ApiService, private val rxBus: RxBus) : AFViewModel<Model>() {

    override val initModel: Model = Model()

    class Model : IModel {
        val email = ObservableString("")
        val password = ObservableString("")
        val progress = ObservableBoolean(false)
    }

    enum class Action : IEvent {
        LoginButtonDidClicked
    }

    sealed class TokenMutation(val value: Token) : IMutation {
        class SetUser(value: Token) : TokenMutation(value)
    }

    sealed class BoolMutation(val value: Boolean) : IMutation {
        class ShowProgress(value: Boolean) : BoolMutation(value)
    }

    override fun mutate(event: IEvent): Observable<IMutation> {
        when (event) {
            Action.LoginButtonDidClicked -> {
                val showProgress = Observable.just(BoolMutation.ShowProgress(true))
                val login = api.getToken(username = currentModel().email.get(), password = currentModel().password.get())
                        .httpScheduler()
                        .map { TokenMutation.SetUser(it) }
                        .doOnError { rxBus.post(RxBusShowToast(it.apiError.message)) }
                        .onErrorResumeNext(Observable.empty())
                val hideProgress = Observable.just(BoolMutation.ShowProgress(false))
                return Observable.concat(showProgress, login, hideProgress)
            }
        }
        return super.mutate(event)
    }

    override fun scan(model: Model, mutation: IMutation): Model {
        when (mutation) {
            is BoolMutation.ShowProgress -> {
                if (!mutation.value) {
                    model.email.set("")
                    model.password.set("")
                }
                model.progress.set(mutation.value)
            }
            is TokenMutation.SetUser -> Log.e("zhaow", "登录结果")
        }
        return model
    }
}
