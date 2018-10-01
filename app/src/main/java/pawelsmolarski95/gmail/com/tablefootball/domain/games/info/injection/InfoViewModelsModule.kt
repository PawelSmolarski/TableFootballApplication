package pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.viewmodel.InfoViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelKey

@Module
abstract class InfoViewModelsModule {
    @IntoMap
    @Binds
    @ViewModelKey(InfoViewModel::class)
    abstract fun infoViewModel(infoViewModel: InfoViewModel): ViewModel
}