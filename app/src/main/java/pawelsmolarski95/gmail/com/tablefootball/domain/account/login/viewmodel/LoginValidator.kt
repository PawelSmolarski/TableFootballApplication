package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

class LoginValidator {
    fun validate(username: String, password: String): Boolean {
        return username != "" && password != ""
    }
}