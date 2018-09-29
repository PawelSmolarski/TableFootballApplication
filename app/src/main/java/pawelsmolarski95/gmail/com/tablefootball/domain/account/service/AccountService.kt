package pawelsmolarski95.gmail.com.tablefootball.domain.account.service

import io.reactivex.Single
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Account
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Token
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("account")
    fun add(@Body account: Account): Single<Account>

    @POST("accounts/login")
    fun login(@Body account: Account): Single<Token>
}

