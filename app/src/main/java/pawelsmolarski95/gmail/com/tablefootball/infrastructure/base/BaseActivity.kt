package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.ActivityModule
import android.widget.ProgressBar
import android.view.ViewGroup
import android.widget.TextView


abstract class BaseActivity : AppCompatActivity() {
    private lateinit var loadingSnackBar: Snackbar

    private val baseViewModels: List<BaseViewModel> by lazy {
        this.provideBaseViewModels()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeLoadingSnackbar()
    }

    private fun initializeLoadingSnackbar() {
        val view = window.decorView.rootView
        loadingSnackBar = Snackbar.make(view, "D", Snackbar.LENGTH_INDEFINITE)
        val contentLay = loadingSnackBar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).parent as ViewGroup
        val item = ProgressBar(this)
        contentLay.addView(item)
    }

    override fun onStart() {
        super.onStart()
        injectActivity()
        prepareBaseViewModels()
    }

    private fun prepareBaseViewModels() {
        baseViewModels
                .forEach { m ->
                    m.takeUnless { it.errorLiveData.hasObservers() }?.errorLiveData?.observe(this, Observer {
                        Toast.makeText(this, m.errorLiveData.value, Toast.LENGTH_SHORT).show()
                    })
                    m.takeUnless { it.loadingLiveData.hasObservers()}?.loadingLiveData?.observe(this, Observer {
                        if (m.loadingLiveData.value == true) {
                            loadingSnackBar.show()
                        } else {
                            loadingSnackBar.dismiss()
                        }
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