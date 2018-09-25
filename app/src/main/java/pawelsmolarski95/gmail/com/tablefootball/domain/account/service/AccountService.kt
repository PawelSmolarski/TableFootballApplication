package pawelsmolarski95.gmail.com.tablefootball.domain.account.service

import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Account
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("account")
    fun add(@Body account: Account): Call<Account>

    @POST("accounts/login")
    fun login(@Body account: Account): Call<Token>
}

