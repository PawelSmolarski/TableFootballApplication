package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.components

import android.support.v4.app.Fragment
import dagger.Component
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.PerFragment
import pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection.modules.FragmentModule

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun fragment(): Fragment
}