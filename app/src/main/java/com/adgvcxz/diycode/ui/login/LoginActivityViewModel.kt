package com.adgvcxz.diycode.ui.login

import android.databinding.ObservableBoolean
import android.util.Log
import com.adgvcxz.*
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

    enum class Mutation(var value: Any? = null) : IMutation {
        SetUser(value = Token()),
        ShowProgress(value = true);
    }

    override fun mutate(event: IEvent): Observable<IMutation> {
        when (event) {
            Action.LoginButtonDidClicked -> {
                val showProgress = Observable.just(Mutation.ShowProgress).doOnNext { it.value = true }
                val login = api.getToken(username = currentModel().email.get(), password = currentModel().password.get())
                        .httpScheduler()
                        .map { token -> Mutation.SetUser.also { it.value = token } }
                        .doOnError { rxBus.post(RxBusShowToast(it.apiError.message)) }
                        .onErrorResumeNext(Observable.empty())
                val hideProgress = Observable.just(Mutation.ShowProgress).doOnNext { it.value = false }
                return Observable.concat(showProgress, login, hideProgress)
            }
        }
        return super.mutate(event)
    }

    override fun scan(model: Model, mutation: IMutation): Model {
        when (mutation as Mutation) {
            Mutation.ShowProgress -> {
                if (!(mutation.value as Boolean)) {
                    model.email.set("")
                    model.password.set("")
                }
                model.progress.set(mutation.value as Boolean)
                Log.e("zhaow", "${mutation.value}")
            }
            Mutation.SetUser -> Log.e("zhaow", "登录结果")
        }
        return model
    }
}
