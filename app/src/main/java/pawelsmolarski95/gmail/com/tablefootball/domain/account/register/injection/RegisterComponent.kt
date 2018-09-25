package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.injection

import dagger.Component
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.view.RegisterActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.PerActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ActivityComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
        modules = [RegisterViewModelsModule::class,
            ActivityModule::class,
            RegisterModule::class])
interface RegisterComponent : ActivityComponent {
    fun inject(registerActivity: RegisterActivity)
}