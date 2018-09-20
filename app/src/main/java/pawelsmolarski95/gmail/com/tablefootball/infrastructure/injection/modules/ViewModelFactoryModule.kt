package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelFactory
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelKey

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}