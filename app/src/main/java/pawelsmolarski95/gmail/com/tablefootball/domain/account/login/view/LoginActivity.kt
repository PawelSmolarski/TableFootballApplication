package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import pawelsmolarski95.gmail.com.tablefootball.R
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.injection.DaggerLoginComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.injection.LoginComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel.LoginViewModel
import pawelsmolarski95.gmail.com.tablefootball.domain.account.register.view.RegisterActivity
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.view.InfoActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.HasComponent
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.reactive.ViewModelFactory
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TableFootballServiceBuilder.Companion.token
import javax.inject.Inject

class LoginActivity : BaseViewActivity(), HasComponent<LoginComponent> {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var loginComponent: LoginComponent
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (token.isNotEmpty()) {
            navigateMainActivity()
        } else {
            setContentView(R.layout.activity_login)
            this.initializeInjector()
            prepareListeners()
            prepareViewModels()
        }
    }

    private fun navigateMainActivity() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun prepareViewModels() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        loginViewModel.loginEvent.observe(this, Observer {
            navigateMainActivity()
        })
    }

    private fun prepareListeners() {
        btnLogin.setOnClickListener {
            loginViewModel.onClickLogin(tietUserName.text.toString(), tietPassword.text.toString())
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeInjector() {
        loginComponent = DaggerLoginComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
        loginComponent.inject(this)
    }

    override fun getComponent(): LoginComponent {
        return loginComponent
    }

    override fun provideBaseViewModels(): List<BaseViewModel> {
        return ArrayList<BaseViewModel>(listOf(loginViewModel))
    }
}