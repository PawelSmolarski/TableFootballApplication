package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.view.LoginActivity
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.LoginViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelKey

@Module
abstract class LoginModule(val view: LoginActivity) {
    @IntoMap
    @Binds
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel
}