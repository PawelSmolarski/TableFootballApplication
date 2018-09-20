package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components

import android.app.Activity
import dagger.Component
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.PerActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ViewModelFactoryModule

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
}