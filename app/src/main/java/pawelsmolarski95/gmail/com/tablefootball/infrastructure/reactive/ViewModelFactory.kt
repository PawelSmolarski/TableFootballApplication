package pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@JvmSuppressWildcards
class ViewModelFactory @Inject constructor(private val viewModels: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel>? = viewModels[modelClass]
        val viewModel: ViewModel = viewModelProvider?.get()
                ?: throw IllegalArgumentException("model class $modelClass not found")

        return viewModel as T
    }
}