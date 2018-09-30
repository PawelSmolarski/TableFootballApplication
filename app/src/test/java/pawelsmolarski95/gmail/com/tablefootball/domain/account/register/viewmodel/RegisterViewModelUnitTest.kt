package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel

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
class RegisterViewModelUnitTest : BaseTest() {
     @Spy
    lateinit var registerValidator: RegisterValidator

    @InjectMocks
    lateinit var registerViewModel: RegisterViewModel

    @Spy
    val accountService: AccountService = prepareMockedAccountService()

    private fun prepareMockedAccountService(): AccountService {
        return AccountMockService(TestServiceBuilder.create(AccountService::class))
    }

    @Test
    fun `when user passed empty username error should appear`() {
        registerViewModel.onClickRegister("" , "pass", "pass", true )
        assertTrue(!registerViewModel.errorLiveData.value.isNullOrEmpty())
    }

    @Test
    fun `when user passed empty password error should appear`() {
        registerViewModel.onClickRegister("user" , "", "pass", true )
        assertTrue(!registerViewModel.errorLiveData.value.isNullOrEmpty())
    }

    @Test
    fun `when user passed not equal passwords error should appear`() {
        registerViewModel.onClickRegister("user" , "pass", "pass2", true )
        assertTrue(!registerViewModel.errorLiveData.value.isNullOrEmpty())
    }

    @Test
    fun `when user passed not checked agreements error should appear`() {
        registerViewModel.onClickRegister("user" , "pass", "pass", false )
        assertTrue(!registerViewModel.errorLiveData.value.isNullOrEmpty())
    }

    @Test
    fun `when user provides appropriate data account is created`() {
        var hasChanged = false
        registerViewModel.registerEvent.observe({ lifecycle }, { hasChanged = true })
        registerViewModel.onClickRegister("user" , "pass", "pass", true )
        assertTrue(hasChanged)
    }
}