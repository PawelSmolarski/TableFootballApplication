package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.SingleLiveData

abstract class BaseViewModel : ViewModel() {
    val errorLiveData: SingleLiveData<String> = SingleLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
}