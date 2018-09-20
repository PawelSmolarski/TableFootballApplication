package pawelsmolarski95.gmail.com.tablefootball.domain.account.login.viewmodel

import javax.inject.Inject

class LoginValidator @Inject constructor(){
    fun validate(username: String, password: String): Boolean {
        if(username != "" && password != "")
            return true

        return false
    }
}