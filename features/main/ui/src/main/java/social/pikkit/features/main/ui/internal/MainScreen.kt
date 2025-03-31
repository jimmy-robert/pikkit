package social.pikkit.features.main.ui.internal

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import social.pikkit.core.ui.PikkitUI
import social.pikkit.core.ui.navigation.Navigation
import social.pikkit.core.ui.navigation.screen
import social.pikkit.features.main.ui.internal.challenge.ChallengeScreen
import social.pikkit.features.main.ui.internal.tabs.MainTabs

private object Routes {
    @Serializable
    data object Tabs

    @Serializable
    data object Challenge
}

@Composable
internal fun MainScreen() {
    PikkitUI.Navigation(startDestination = Routes.Tabs) {
        screen<Routes.Tabs> {
            MainTabs { navController.navigate(Routes.Challenge) }
        }

        screen<Routes.Challenge> {
            ChallengeScreen(
                onFinish = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}