package pawelsmolarski95.gmail.com.tablefootball.domain.games.info.viewmodel

import pawelsmolarski95.gmail.com.tablefootball.domain.games.service.InfoService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseViewModel
import javax.inject.Inject

class InfoViewModel @Inject constructor(private val infoService: InfoService) : BaseViewModel() {

}