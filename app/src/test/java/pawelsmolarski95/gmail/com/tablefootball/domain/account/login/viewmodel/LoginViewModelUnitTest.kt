package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import pawelsmolarski95.gmail.com.tablefootball.domain.account.AccountMockService
import pawelsmolarski95.gmail.com.tablefootball.domain.account.service.AccountService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TestServiceBuilder
import util.BaseTest


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelUnitTest : BaseTest() {
    @Spy
    lateinit var loginValidator: LoginValidator

    @InjectMocks
    lateinit var loginViewModel: LoginViewModel

    @Spy
    val accountService: AccountService = prepareMockedAccountService()

    private fun prepareMockedAccountService(): AccountService {
        return AccountMockService(TestServiceBuilder.create(AccountService::class))
    }

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
    fun `when user provides appropriate data login would happen`() {
        var hasChanged = false
        loginViewModel.loginEvent.observe({ lifecycle }, { hasChanged = true })
        loginViewModel.onClickLogin("user" , "pass")
        assertTrue(hasChanged)
    }

    @Test
    fun `when login and password is not empty error should not appear`() {
        loginViewModel.onClickLogin("TEST", "TEST")
        assertTrue(loginViewModel.errorLiveData.value.isNullOrEmpty())
    }
}