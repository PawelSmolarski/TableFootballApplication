package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelFactory

abstract class BaseActivity : AppCompatActivity() {
    private val baseViewModels: List<BaseViewModel> by lazy {
        this.provideBaseViewModels()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        prepareBaseViewModels()
    }

    private fun prepareBaseViewModels() {
        baseViewModels.forEach { m ->
            m.errorLiveData.observe(this, Observer {
                Toast.makeText(this, m.errorLiveData.value, Toast.LENGTH_SHORT).show()
            })
        }
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

    protected abstract fun provideBaseViewModels(): List<BaseViewModel>
}