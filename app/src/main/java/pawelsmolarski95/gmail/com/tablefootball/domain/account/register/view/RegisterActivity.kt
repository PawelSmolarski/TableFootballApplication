package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import pawelsmolarski95.gmail.com.tablefootball.R
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.injection.DaggerRegisterComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.injection.RegisterComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel.RegisterViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.HasComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelFactory
import javax.inject.Inject

class RegisterActivity : BaseViewActivity(), HasComponent<RegisterComponent> {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var registerComponent: RegisterComponent
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_register)
        this.initializeInjector()
        prepareListeners()
        prepareViewModels()
        super.onCreate(savedInstanceState)
    }

    private fun prepareViewModels() {
        registerViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)
        registerViewModel.registerEvent.observe(this, Observer {
            Toast.makeText(this, "Register succeed", Toast.LENGTH_SHORT).show()
            finish()
        })
    }

    private fun prepareListeners() {
        btnRegister.setOnClickListener {
            registerViewModel.onClickRegister(tietUserName.text.toString(), tietPassword.text.toString(), tietPasswordConfirmation.text.toString(), chkAgreements.isChecked)
        }
    }

    private fun initializeInjector() {
        registerComponent = DaggerRegisterComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
        registerComponent.inject(this)
    }

    override fun getComponent(): RegisterComponent {
        return registerComponent
    }

    override fun provideBaseViewModels(): List<BaseViewModel> {
        return ArrayList<BaseViewModel>(listOf(registerViewModel))
    }
}