package pawelsmolarski95.gmail.com.tablefootball.domain.games.info.view

import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_info.*
import pawelsmolarski95.gmail.com.tablefootball.R
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection.DaggerInfoComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection.InfoComponent
import pawelsmolarski95.gmail.com.tablefootball.domain.games.info.injection.InfoModule
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.BaseActivity
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.base.Navigator
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.HasComponent

class InfoActivity : BaseActivity(), HasComponent<InfoComponent> {
    private lateinit var infoComponent: InfoComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val infoFragment = InfoFragment()
        Navigator.INSTANCE.initializeHomeFragment(infoFragment as Fragment, flInfoFragmentContainer, this)
        this.initializeInjector()
    }

    private fun initializeInjector() {
        infoComponent = DaggerInfoComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .infoModule(InfoModule())
                .build()
    }

    override fun getComponent(): InfoComponent {
        return infoComponent
    }

    override fun onBackPressed() {
        if (!Navigator.INSTANCE.onBackPressed())
            super.onBackPressed()
    }
}