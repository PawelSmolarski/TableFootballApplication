package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelUnitTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Spy
    lateinit var loginValidator: LoginValidator

    @InjectMocks
    lateinit var loginViewModel: LoginViewModel

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

    @Test
    fun `when login and password is not empty error should not appear`() {
        loginViewModel.onClickLogin("TEST", "TEST")
        assertTrue(loginViewModel.errorLiveData.value.isNullOrEmpty())
    }
}