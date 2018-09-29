package pawelsmolarski95.gmail.com.tablefootball.domain.account.register.viewmodel

class RegisterValidator {
    fun validate(username: String,
                 password: String,
                 confirmedPassword: String,
                 agreementsChecked: Boolean): RegisterValidatorState {
        return if (!validateAgreements(agreementsChecked)) {
            RegisterValidatorState.AGREEMENTS_NOT_CHECKED
        } else if (!validateUsername(username)) {
            RegisterValidatorState.USERNAME_BLANK
        } else if (!validatePassword(password, confirmedPassword)) {
            RegisterValidatorState.PASSWORD_NOT_EQUAL_OR_BLANK
        } else {
            RegisterValidatorState.OK
        }
    }

    private fun validatePassword(password: String, confirmedPassword: String): Boolean {
        return !(!password.isEmpty() && !confirmedPassword.isEmpty() && password != confirmedPassword)

    }

    private fun validateUsername(username: String): Boolean {
        return username.isNotBlank()
    }

    private fun validateAgreements(agreementsChecked: Boolean): Boolean {
        return agreementsChecked
    }

    enum class RegisterValidatorState(val message: String) {
        OK(""),
        AGREEMENTS_NOT_CHECKED("Agreements not checked"),
        USERNAME_BLANK("Username cannot be blank"),
        PASSWORD_NOT_EQUAL_OR_BLANK("Passwords has to be the same and cannot be blank")
    }
}