package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelUnitTest {
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h -> Schedulers.trampoline() }
    }

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Spy
    lateinit var loginValidator: LoginValidator

    @InjectMocks
    lateinit var loginViewModel: LoginViewModel

    @Spy
    lateinit var accountService: AccountService

    @Test
    fun `when username and password is empty error should appear`() {
        loginViewModel.onClickLogin("", "")
        assertTrue(!loginViewModel.errorLiveData.value.isNullOrEmpty())
    }

    @Test
    fun `when username is empty error should appear`() {
        loginViewModel.onClickLogin("", "TEST")
        assertTrue(!loginViewModel.errorLiveData.value.isNullOrEmpty())
    }

    @Test
    fun `when password is empty error should appear`() {
        loginViewModel.onClickLogin("TEST", "")
        assertTrue(!loginViewModel.errorLiveData.value.isNullOrEmpty())
    }
}