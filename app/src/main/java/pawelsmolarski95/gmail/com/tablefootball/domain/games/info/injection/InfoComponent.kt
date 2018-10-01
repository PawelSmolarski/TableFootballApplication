package pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection

import dagger.Component
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.view.InfoFragment
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.PerActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ActivityComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, InfoModule::class, InfoViewModelsModule::class])
interface InfoComponent : ActivityComponent {
    fun inject(infoFragment: InfoFragment)
}