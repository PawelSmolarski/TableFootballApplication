package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.app.Application
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.DaggerApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ApplicationModule

class AndroidApplication : Application() {
    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        this.initializeInjector()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}