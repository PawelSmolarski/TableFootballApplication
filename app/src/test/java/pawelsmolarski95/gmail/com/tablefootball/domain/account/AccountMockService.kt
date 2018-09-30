package pawelsmolarski95.gmail.com.tablefootball.domain.account

import io.reactivex.Single
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Account
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.Token
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import retrofit2.mock.BehaviorDelegate

class AccountMockService(val delegate: BehaviorDelegate<out AccountService>) : AccountService {
    override fun add(account: Account): Single<Account> {
        return delegate.returningResponse(Account("test", "test")).add(Account("test", "test"))
    }

    override fun login(account: Account): Single<Token> {
        return delegate.returningResponse(Token("testToken", Account("test", "test"))).login(Account("test", "test"))
    }
}