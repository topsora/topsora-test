package com.and.sample

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.and.sample.NavigationScreens.BOTTOM_SHEET
import com.and.sample.NavigationScreens.CHANGE_PASSWORD_SCREEN
import com.and.sample.NavigationScreens.LOGIN_SCREEN
import com.and.sample.NavigationScreens.MAP_SCREEN
import com.and.sample.NavigationScreens.NEW_MAP_SCREEN
import com.and.sample.NavigationScreens.SEARCH_SCREEN
import com.and.sample.NavigationScreens.SELECT_POST_OFFICE_SCREEN

object NavigationScreens {
    const val LOGIN_SCREEN = "login_screen"
    const val CHANGE_PASSWORD_SCREEN = "change_password_screen"
    const val SELECT_POST_OFFICE_SCREEN = "select_post_office_screen"
    const val MAP_SCREEN = "map_screen"
    const val NEW_MAP_SCREEN = "new_map_screen"
    const val SEARCH_SCREEN = "search_screen"

    //    test route
    const val BOTTOM_SHEET = "bottom_sheet"
    const val TEST1 = "test1_route"
    const val TEST2 = "test2_route"
    const val TEST3 = "test3_route"
}

class NavigationActions(
    private val navController: NavHostController,
) {
    fun navigateToLoginScreen() {
        navController.navigate(LOGIN_SCREEN)
    }

    fun navigateToChangePasswordScreen() {
        navController.navigate(CHANGE_PASSWORD_SCREEN)
    }

    fun navigateToSelectPostOfficeScreen() {
        navController.navigate(SELECT_POST_OFFICE_SCREEN)
    }

    fun navigateToMapScreen() {
        navController.navigate(MAP_SCREEN) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToNewMapScreen() {
        navController.navigate(NEW_MAP_SCREEN)
    }

    fun navigateToSearchScreen() {
        navController.navigate(SEARCH_SCREEN)
    }

    fun navigateToBottomSheetScreen() {
        navController.navigate(BOTTOM_SHEET)
    }

    fun navigateToTest1() {
        navController.navigate(NavigationScreens.TEST1)
    }

    fun navigateToTest2() {
        navController.navigate(NavigationScreens.TEST2)
    }

    fun navigateToTest3() {
        navController.navigate(NavigationScreens.TEST3)
    }
}
