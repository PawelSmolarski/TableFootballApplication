package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.SingleLiveEvent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TableFootballServiceBuilder
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginValidator: LoginValidator, private val accountService: AccountService) : BaseViewModel() {
    val loginEvent = SingleLiveEvent()

    fun onClickLogin(username: String, password: String) {
        val state = loginValidator.validate(username, password)
        when (state) {
            LoginValidator.LoginValidatorState.OK -> loginRequest(username, password)
            else -> errorLiveData.value = state.message
        }
    }

    private fun loginRequest(username: String, password: String) {
        val call = accountService.login(Account(username, password))
        val rx = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingLiveData.value = true }
                .subscribeWith(
                        object : DisposableSingleObserver<Token>() {
                            override fun onSuccess(t: Token) {
                                Log.i("Request", "OnResponse $t")
                                loadingLiveData.value = false
                                TableFootballServiceBuilder.token = t.token
                                loginEvent.call()
                            }

                            override fun onError(e: Throwable) {
                                Log.i("Request", "OnFailure ${e.message}")
                                loadingLiveData.value = false
                                errorLiveData.value = "Try again"
                            }
                        })
    }
}