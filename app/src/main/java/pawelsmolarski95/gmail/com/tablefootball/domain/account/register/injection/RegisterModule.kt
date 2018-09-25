package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.injection

import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel.RegisterValidator

@Module
class RegisterModule {
    @Provides
    fun registerValidator(): RegisterValidator {
        return RegisterValidator()
    }
}