package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components

import android.content.Context
import android.content.res.Resources
import dagger.Component
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ApplicationModule
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ViewModelFactoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelFactoryModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context

    fun resources(): Resources
}