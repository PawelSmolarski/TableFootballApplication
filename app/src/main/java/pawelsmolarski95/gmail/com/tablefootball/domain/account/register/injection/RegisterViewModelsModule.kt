package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel.RegisterViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelKey

@Module
abstract class RegisterViewModelsModule {
    @IntoMap
    @Binds
    @ViewModelKey(RegisterViewModel::class)
    abstract fun registerViewModel(registerViewModel: RegisterViewModel): ViewModel
}