package social.pikkit.features.settings.ui.internal

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import social.pikkit.core.ui.wip.WorkInProgress
import social.pikkit.features.settings.ui.internal.home.SettingsHomeScreen

internal object SettingsRoutes {
    @Serializable
    object Home

    @Serializable
    object WorkInProgress
}

@Composable
internal fun SettingsScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SettingsRoutes.Home
    ) {
        composable<SettingsRoutes.Home> {
            SettingsHomeScreen(
                navController = navController
            )
        }

        composable<SettingsRoutes.WorkInProgress> {
            WorkInProgress()
        }
    }
}