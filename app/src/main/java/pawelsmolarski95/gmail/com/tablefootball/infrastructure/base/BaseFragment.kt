package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.HasComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components.ApplicationComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.FragmentModule

abstract class BaseFragment : Fragment() {
    private var isInjected: Boolean = false
    private val baseViewModels: List<BaseViewModel> by lazy {
        this.provideBaseViewModels()
    }

    protected abstract fun initializeInjector()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        retainInstance = true

        isInjected = try {
            onInjectView()
        } catch (e: IllegalStateException) {
            Log.e("Injection Error", e.message)
            false
        }
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

    protected fun getApplicationComponent(): ApplicationComponent? {
        if (activity == null)
            throw IllegalStateException("Lack of component")

        return (((activity as Activity).application) as AndroidApplication).applicationComponent
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Navigator.INSTANCE.setCurrentFragment(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isInjected)
            isInjected = onInjectView()
    }

    @Throws(IllegalStateException::class)
    protected fun <C> getComponent(componentType: Class<C>): C {
        if (activity == null)
            throw IllegalStateException("Activity is null")

        return componentType.cast((activity as HasComponent<*>).getComponent())
                ?: throw IllegalStateException(componentType.simpleName + " has not been initialized yet.")
    }

    @Throws(IllegalStateException::class)
    protected open fun onInjectView(): Boolean {
        return false
    }

    protected fun getFragmentModule(): FragmentModule {
        return FragmentModule(this)
    }

    protected abstract fun provideBaseViewModels(): List<BaseViewModel>
}