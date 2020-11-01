package com.gonzoapps.planttracker
//
//import androidx.fragment.app.testing.launchFragmentInContainer
//import androidx.navigation.Navigation
//import androidx.navigation.testing.TestNavHostController
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions
//import androidx.test.espresso.matcher.ViewMatchers
//import org.junit.Test
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import com.gonzoapps.planttracker.screens.welcome.WelcomeFragment
//import com.google.common.truth.Truth.assertThat
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class NavigationUnitTest {
//    @Test
//    fun testNavigationToWelcomeScreen() {
//        val navController = TestNavHostController(
//                ApplicationProvider.getApplicationContext())
//        navController.setGraph(R.navigation.main_navigation)
//
//
//        val loginScenario = launchFragmentInContainer<WelcomeFragment>()
//
//        loginScenario.onFragment { fragment ->
//            Navigation.setViewNavController(fragment.requireView(), navController)
//
//            // Verify that performing a click changes the NavController’s state
//            onView(ViewMatchers.withId(R.id.button_login)).perform(ViewActions.click())
//            assertThat(navController.currentDestination?.id).isEqualTo(R.id.welcomeFragment)
//        }
//
//    }
//}