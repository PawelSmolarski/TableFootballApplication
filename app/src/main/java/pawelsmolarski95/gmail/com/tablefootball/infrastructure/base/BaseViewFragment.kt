package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.widget.Toast

abstract class BaseViewFragment : BaseFragment() {
    private val baseViewModels: List<BaseViewModel> by lazy {
        this.provideBaseViewModels()
    }

    protected abstract fun initializeInjector()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        initializeInjector()
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        prepareBaseViewModels()
    }

    private fun prepareBaseViewModels() {
        baseViewModels.forEach { m ->
            m.errorLiveData.observe(this, Observer {
                Toast.makeText(context, m.errorLiveData.value, Toast.LENGTH_SHORT).show()
            })
        }
    }

    protected abstract fun provideBaseViewModels(): List<BaseViewModel>
}