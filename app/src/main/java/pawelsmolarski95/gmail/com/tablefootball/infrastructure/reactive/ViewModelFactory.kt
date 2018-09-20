package pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@JvmSuppressWildcards
class ViewModelFactory @Inject constructor(private val viewModels: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel>? = viewModels.get(modelClass)
        val viewModel: ViewModel = viewModelProvider?.get()
                ?: throw IllegalArgumentException("model class ${modelClass} not found")

        return viewModel as T
    }
}