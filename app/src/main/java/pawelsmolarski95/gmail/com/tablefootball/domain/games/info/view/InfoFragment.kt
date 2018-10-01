package pawelsmolarski95.gmail.com.tablefootball.domain.games.info.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pawelsmolarski95.gmail.com.tablefootball.R
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.LoginViewModel
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection.InfoComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.viewmodel.InfoViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewFragment
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelFactory
import javax.inject.Inject

class InfoFragment : BaseViewFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var infoViewModel: InfoViewModel

    override fun provideBaseViewModels(): List<BaseViewModel> {
        return ArrayList<BaseViewModel>(listOf(infoViewModel))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentView: View = inflater.inflate(R.layout.fragment_info, container, false)
        prepareViewModels()

        return fragmentView
    }

    private fun prepareViewModels() {
        infoViewModel = ViewModelProviders.of(this, viewModelFactory).get(InfoViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        // todo ps logic
    }

    @Throws(IllegalStateException::class)
    override fun onInjectView(): Boolean {
        getComponent(InfoComponent::class.java).inject(this)
        return true
    }

    override fun initializeInjector() {
        //does nothing
    }
}