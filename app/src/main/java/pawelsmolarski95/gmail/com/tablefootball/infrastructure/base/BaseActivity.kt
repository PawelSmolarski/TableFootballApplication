package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule

abstract class BaseActivity : AppCompatActivity() {
    @CallSuper
    override fun onStart() {
        super.onStart()
        injectActivity()
    }

    private fun injectActivity() {
        getApplicationComponent()?.inject(this)
    }

    protected fun getApplicationComponent(): ApplicationComponent? {
        return (application as AndroidApplication).applicationComponent
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }
}