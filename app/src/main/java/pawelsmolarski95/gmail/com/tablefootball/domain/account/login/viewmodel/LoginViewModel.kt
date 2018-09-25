package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import android.util.Log
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.SingleLiveEvent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TableFootballServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginValidator: LoginValidator) : BaseViewModel() {
    val loginEvent = SingleLiveEvent()

    fun onClickLogin(username: String, password: String) {
        if (loginValidator.validate(username, password))
            loginRequest(username, password)
        else
            errorLiveData.value = "Username or password should not be empty"
    }

    private fun loginRequest(username: String, password: String) {
        val call = TableFootballServiceBuilder.create(AccountService::class).login(Account(username, password))
        Log.i("CALL:", call.toString() + " " + call.request().url())

        call.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.i("Request", "OnFailure ${t.message}")
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.i("Request", "OnResponse ${response.body()?.toString()}")
            }
        })
    }
}