package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.Snackbar
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import pawelsmolarski95.gmail.com.tablefootball.R

abstract class BaseViewActivity : BaseActivity() {
    private lateinit var loadingSnackBar: Snackbar
    private lateinit var errorSnackBar: Snackbar

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
        loadingSnackBar = Snackbar.make(view, R.string.loading, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar = Snackbar.make(view, "Error", Snackbar.LENGTH_SHORT)
        val contentLay = loadingSnackBar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).parent as ViewGroup
        val item = ProgressBar(this)
        contentLay.addView(item)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        prepareBaseViewModels()
    }

    private fun prepareBaseViewModels() {
        baseViewModels
                .forEach { m ->
                    m.takeUnless { it.errorLiveData.hasObservers() }?.errorLiveData?.observe(this, Observer {
                        errorSnackBar.setText(m.errorLiveData.value.toString()).show()
                    })
                    m.takeUnless { it.loadingLiveData.hasObservers() }?.loadingLiveData?.observe(this, Observer {
                        if (m.loadingLiveData.value == true) {
                            loadingSnackBar.show()
                        } else {
                            loadingSnackBar.dismiss()
                        }
                    })
                }
    }

    protected abstract fun provideBaseViewModels(): List<BaseViewModel>
}