package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}