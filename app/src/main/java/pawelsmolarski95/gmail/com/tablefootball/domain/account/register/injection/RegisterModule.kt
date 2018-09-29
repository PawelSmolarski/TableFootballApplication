package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.injection

import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel.RegisterValidator
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TableFootballServiceBuilder

@Module
class RegisterModule {
    @Provides
    fun registerValidator(): RegisterValidator {
        return RegisterValidator()
    }

    @Provides
    fun accountService(): AccountService {
        return TableFootballServiceBuilder.create(AccountService::class)
    }
}