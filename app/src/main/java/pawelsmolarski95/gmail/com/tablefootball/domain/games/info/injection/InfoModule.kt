package pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection

import dagger.Module
import dagger.Provides
import pawelsmolarski95.gmail.com.tablefootball.domain.games.service.InfoService
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.web.TableFootballServiceBuilder

@Module
class InfoModule {
    @Provides
    fun infoService(): InfoService {
        return TableFootballServiceBuilder.create(InfoService::class)
    }
}