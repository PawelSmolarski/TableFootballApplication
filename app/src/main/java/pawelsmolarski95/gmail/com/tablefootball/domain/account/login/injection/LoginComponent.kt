package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.injection

import dagger.Component
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.view.LoginActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.PerActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ActivityComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
        modules = [LoginViewModelsModule::class,
            ActivityModule::class,
            LoginModule::class])
interface LoginComponent : ActivityComponent {
    fun inject(loginActivity: LoginActivity)
}