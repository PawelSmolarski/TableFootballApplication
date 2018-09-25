package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.PerActivity

@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    @PerActivity
    fun activity(): Activity {
        return activity
    }
}