package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Account
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.SingleLiveEvent
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val registerValidator: RegisterValidator, private val accountService: AccountService) : BaseViewModel() {
    val registerEvent: SingleLiveEvent = SingleLiveEvent()

    fun onClickRegister(username: String, password: String, passwordConfirmed: String, agreementsChecked: Boolean) {
        val state = registerValidator.validate(username, password, passwordConfirmed, agreementsChecked)
        when (state) {
            RegisterValidator.RegisterValidatorState.OK -> registerRequest(username, password)
            else -> errorLiveData.value = state.message
        }
    }

    private fun registerRequest(username: String, password: String) {
        val call = accountService.add(Account(username, password))
        val rx = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingLiveData.value = true }
                .subscribeWith(
                        object : DisposableSingleObserver<Account>() {
                            override fun onSuccess(a: Account) {
                                Log.i("Request", "OnResponse $a")
                                loadingLiveData.value = false
                                registerEvent.call()
                            }

                            override fun onError(e: Throwable) {
                                Log.i("Request", "OnFailure ${e.message}")
                                loadingLiveData.value = false
                                errorLiveData.value = "Try again"
                            }
                        })
    }
}