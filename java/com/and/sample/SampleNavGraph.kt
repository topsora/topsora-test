package com.and.sample

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.sample.screen.changepassword.ChangePasswordScreen
import com.and.sample.screen.login.LoginScreen
import com.and.sample.screen.map.MapScreen
import com.and.sample.screen.permission.Permission
import com.and.sample.screen.search.SearchScreen
import com.and.sample.screen.selectpostoffice.SelectPostOfficeScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
//    startDestination: String = NavigationScreens.LOGIN_SCREEN,
    startDestination: String = NavigationScreens.MAP_SCREEN,
    navActions: NavigationActions =
        remember(navController) {
            NavigationActions(navController)
        },
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            NavigationScreens.LOGIN_SCREEN,
        ) {
            LoginScreen(
                onNext = { navActions.navigateToChangePasswordScreen() },
            )
        }
        composable(
            NavigationScreens.CHANGE_PASSWORD_SCREEN,
        ) {
            ChangePasswordScreen(onBack = { navController.popBackStack() }, onNext = {
                navActions.navigateToSelectPostOfficeScreen()
            })
        }
        composable(
            NavigationScreens.SELECT_POST_OFFICE_SCREEN,
        ) {
            SelectPostOfficeScreen(
                onBack = {
                    navController.popBackStack()
                },
                onNext = {
                    navActions.navigateToMapScreen()
                },
            )
        }
        composable(
            NavigationScreens.MAP_SCREEN,
        ) {
            MapScreen()
        }
        composable(
            NavigationScreens.SEARCH_SCREEN,
        ) {
            SearchScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSearch = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("key", "abc")
                    navController.popBackStack()
                },
            )
        }

        composable(
            NavigationScreens.BOTTOM_SHEET,
        ) {
        }

        composable(
            NavigationScreens.TEST1,
        ) {
            Permission()
        }

        composable(
            NavigationScreens.TEST2,
        ) {
        }

        composable(
            NavigationScreens.TEST3,
        ) {
        }
    }
}
