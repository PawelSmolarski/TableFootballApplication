package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import android.arch.lifecycle.ViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginValidator: LoginValidator) : BaseViewModel() {
    val loginEvent = SingleLiveEvent()

    fun onClickLogin(username: String, password: String) {
        if (loginValidator.validate(username, password))
            loginEvent.call()
        else
            errorLiveData.value = "ERROR APPEARED"
    }
}