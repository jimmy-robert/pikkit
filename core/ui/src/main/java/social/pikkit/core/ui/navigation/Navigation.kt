package social.pikkit.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import social.pikkit.core.ui.PikkitUI

@Composable
fun PikkitUI.Companion.Navigation(
    startDestination: Any,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    builder: NavGraphBuilder.() -> Unit
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination,
            builder = builder
        )
    }
}

data class NavigationScreen<T>(
    val navBackStackEntry: NavBackStackEntry,
    val route: T,
    val navController: NavHostController
)

inline fun <reified T : Any> NavGraphBuilder.screen(
    crossinline builder: @Composable NavigationScreen<T>.() -> Unit
) {
    composable<T> { navBackStackEntry ->
        val navController = LocalNavController.current
        remember(navBackStackEntry, navController) {
            NavigationScreen(
                navBackStackEntry = navBackStackEntry,
                route = navBackStackEntry.toRoute<T>(),
                navController = navController
            )
        }.builder()
    }
}

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("LocalNavController should never be null here")
}

fun NavHostController.navigateAndClear(route: Any) {
    navigate(route) {
        popUpTo(graph.id) { inclusive = true }
    }
}
