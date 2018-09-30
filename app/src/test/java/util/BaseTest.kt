package util

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito

abstract class BaseTest {
    protected var lifecycle: LifecycleRegistry = initializeLifeCycle()

    private fun initializeLifeCycle(): LifecycleRegistry {
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        return lifecycle
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { h -> Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { h -> Schedulers.trampoline() }
        }
    }

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
}