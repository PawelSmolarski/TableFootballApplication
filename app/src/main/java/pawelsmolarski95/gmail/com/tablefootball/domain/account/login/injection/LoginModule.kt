package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.injection

import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.LoginValidator
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TableFootballServiceBuilder

@Module
class LoginModule {
    @Provides
    fun loginValidator(): LoginValidator {
        return LoginValidator()
    }

    @Provides
    fun accountService(): AccountService {
        return TableFootballServiceBuilder.create(AccountService::class)
    }
}