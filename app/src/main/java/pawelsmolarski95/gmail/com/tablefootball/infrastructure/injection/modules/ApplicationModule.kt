package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.AndroidApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return this.application.applicationContext
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        return application.resources
    }
}