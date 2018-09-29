package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

class LoginValidator {
    fun validate(username: String, password: String): LoginValidatorState {
        return if(username.isNotBlank() && password.isNotBlank())
            LoginValidatorState.OK
        else
            LoginValidatorState.USERNAME_OR_PASSWORD_EMPTY

    }

    enum class LoginValidatorState(val message: String) {
        OK(""),
        USERNAME_OR_PASSWORD_EMPTY("Username or password should not be empty")
    }
}

