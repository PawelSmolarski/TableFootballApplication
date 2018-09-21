package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.injection

import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.LoginValidator

@Module
class LoginModule {
    @Provides
    fun loginValidator(): LoginValidator {
        return LoginValidator()
    }
}