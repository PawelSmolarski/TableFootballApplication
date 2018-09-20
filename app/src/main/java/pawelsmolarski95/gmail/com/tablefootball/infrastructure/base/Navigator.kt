package pawelsmolarski95.gmail.com.tablefootball.infrastructure.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import java.util.*

enum class Navigator {
    INSTANCE;

    private val fragmentBackStack: Stack<Fragment> = Stack()
    private val HOME_FRAGMENT_TAG: String = "homeFragment"
    private var homeFragment: Fragment? = null
    private var currentFragment: Fragment? = null
    private var frameLayoutId: Int = 0

    /**
     * Method to set field with currentFragment. Need to restore application.
     *
     * @param currentFragment to be restored
     */
    fun setCurrentFragment(currentFragment: Fragment) {
        this.currentFragment = currentFragment
    }

    /**
     *  Initializes fragment as home one. Should be called in onCreate method in main activity.
     *
     * @param homeFragment to be set as home
     * @param frameLayout for keeping fragment
     * @param activity to be filled out
     */
    fun initializeHomeFragment(homeFragment: Fragment, frameLayout: FrameLayout, activity: AppCompatActivity) {
        if (this.homeFragment == null) {
            initializeView(homeFragment, frameLayout, activity)
        } else {
            if ((this.homeFragment as Fragment).isAdded || fragmentBackStack.contains(this.homeFragment) && this.homeFragment != this.currentFragment)
                return

            restoreView(activity, frameLayout)
        }
    }

    private fun initializeView(homeFragment: Fragment, frameLayout: FrameLayout, activity: AppCompatActivity) {
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        frameLayout.removeAllViews()
        fragmentTransaction.add(frameLayout.id, homeFragment, HOME_FRAGMENT_TAG)
        fragmentTransaction.commit()

        this.homeFragment = homeFragment
        this.frameLayoutId = frameLayout.id
        this.currentFragment = homeFragment
        fragmentBackStack.push(homeFragment)
    }

    private fun restoreView(activity: AppCompatActivity, frameLayout: FrameLayout) {
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(frameLayout.id, this.homeFragment, HOME_FRAGMENT_TAG)
        fragmentTransaction.commit()
    }

    /**
     * Sets given fragment as home one. Should be called in onCreateView method.
     *
     * @param fragment to be setted as home
     */
    fun setHomeFragment(fragment: Fragment) {
        this.homeFragment = fragment
        resetFragmentBackStack(true)
    }

    /**
     * Method which navigates to specific fragment. It sets this fragment at the top of stack of fragments
     *
     * The logic behind combinations of {@param saveBackStack} and {@param resetBackStack}
     *  false - false => parent fragment would not be kept and previous stack would not be reset
     *  true - false => parent fragment would be kept and previous stack would not be reset
     *  false - true => parent fragment would not be kept and stack would be reset to home fragment
     *  true - true => parent fragment would be kept and stack would be reset to home
     *
     * @param childFragment to be set as current one
     * @param bundle to be set to next fragment with usage of {@link android.support.v4.app.Fragment#setArguments(Bundle)}
     * @param saveBackStack flag which indicates if current fragment has to be saved in back-stack
     * @param resetBackStack flag which indicates if back-stack has to be reset, what means that after clicking back the home activity would be restored
     *
     */
    fun navigateView(childFragment: Fragment, bundle: Bundle?, saveBackStack: Boolean, resetBackStack: Boolean) {
        this.resetFragmentBackStack(resetBackStack)
        this.saveCurrentFragmentToBackStack(saveBackStack)

        if (bundle != null)
            childFragment.arguments = bundle

        val fragmentManager: FragmentManager = currentFragment?.fragmentManager
                ?: throw IllegalStateException("Fragment manager of current fragment is null")

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(frameLayoutId, childFragment)
        currentFragment = childFragment
        fragmentTransaction.commit()
    }

    private fun saveCurrentFragmentToBackStack(saveBackStack: Boolean) {
        val currentFragment: Fragment = currentFragment
                ?: throw IllegalStateException("CurrentFragment can not be null")
        val homeFragment: Fragment = homeFragment
                ?: throw IllegalStateException("HomeFragment can not be null")

        if (saveBackStack || (currentFragment::class == homeFragment::class && !fragmentBackStack.contains(homeFragment)))
            fragmentBackStack.push(currentFragment)
    }

    private fun resetFragmentBackStack(resetBackStack: Boolean) {
        if (resetBackStack) {
            fragmentBackStack.removeAllElements()
            fragmentBackStack.push(homeFragment)
        }
    }

    /**
     * Method to be called in onBackPressed method in main activity to decide what to do with current
     * fragment of backtrace.
     *
     * @return false if current fragment is last one
     */
    fun onBackPressed(): Boolean {
        if (fragmentBackStack.isEmpty())
            return false

        if (fragmentBackStack.size == 1 && (fragmentBackStack.lastElement() == homeFragment && currentFragment == homeFragment))
            return false

        try {
            val lastFragment: Fragment = fragmentBackStack.pop()
            val bundle: Bundle? = currentFragment?.arguments
            navigateView(lastFragment, bundle, false, false)
            return true
        }catch(e: EmptyStackException) {
            Log.e("EMPTY STACK", e.message, e)
        }

        return false
    }
}