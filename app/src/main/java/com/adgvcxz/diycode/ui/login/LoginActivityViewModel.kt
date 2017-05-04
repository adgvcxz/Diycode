package com.adgvcxz.diycode.ui.login

import android.databinding.ObservableBoolean
import android.util.Log
import com.adgvcxz.IAction
import com.adgvcxz.IMutation
import com.adgvcxz.IState
import com.adgvcxz.ViewModel
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.ui.login.LoginActivityViewModel.State
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class LoginActivityViewModel @Inject constructor() : ViewModel<State>(State()) {

    class State : IState {
        val email = ObservableString("")
        val password = ObservableString("")
        val progress = ObservableBoolean(false)
    }

    enum class Action : IAction {
        LoginButtonDidClicked
    }

    enum class Mutation(var value: Any? = null) : IMutation {
        Login,
        ShowProgress(value = true);
    }

    override fun mutate(action: IAction): Observable<IMutation> {
        when (action) {
            Action.LoginButtonDidClicked -> {
                val showProgress = Observable.just(Mutation.ShowProgress)
                val login = Observable.just(Mutation.Login).delay(3, TimeUnit.SECONDS)
                val hideProgress = Observable.just(Mutation.ShowProgress).doOnNext { it.value = false }
                return Observable.concat(showProgress, login, hideProgress)
            }
        }
        return super.mutate(action)
    }

    override fun scan(state: State, mutation: IMutation): State {
        when (mutation) {
            Mutation.ShowProgress -> {
                if (!((mutation as Mutation).value as Boolean)) {
                    state.email.set("")
                    state.password.set("")
                }
                state.progress.set(mutation.value as Boolean)
            }
            Mutation.Login -> Log.e("zhaow", "Login...")
        }
        return state
    }
}
