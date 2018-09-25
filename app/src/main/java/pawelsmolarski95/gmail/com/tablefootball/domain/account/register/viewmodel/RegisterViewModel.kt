package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel

import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.SingleLiveEvent
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val registerValidator: RegisterValidator) : BaseViewModel() {
    val registerEvent: SingleLiveEvent = SingleLiveEvent()

    fun onClickRegister(username: String, password: String, passwordConfirmed: String) {
        // todo ps
    }
}