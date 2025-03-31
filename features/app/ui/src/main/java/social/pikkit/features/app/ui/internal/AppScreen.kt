package social.pikkit.features.app.ui.internal

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import social.pikkit.core.ui.PikkitUI
import social.pikkit.core.ui.navigation.Navigation
import social.pikkit.core.ui.navigation.navigateAndClear
import social.pikkit.core.ui.navigation.screen
import social.pikkit.core.ui.theme.Theme
import social.pikkit.features.main.ui.MainUI
import social.pikkit.features.settings.Settings

private object Routes {
    @Serializable
    data object Authentication

    @Serializable
    data object Main
}

@Composable
internal fun AppScreen(
    viewModel: AppViewModel = koinViewModel()
) {
    val theme by viewModel.themeState.collectAsStateWithLifecycle()

    PikkitUI.Theme(
        darkTheme = when (theme) {
            Settings.Theme.System -> isSystemInDarkTheme()
            else -> theme == Settings.Theme.Dark
        }
    ) {
        PikkitUI.Navigation(
            startDestination = remember {
                when {
                    viewModel.isAuthenticated() -> Routes.Main
                    else -> Routes.Authentication
                }
            }
        ) {
            screen<Routes.Authentication> {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        Button(
                            onClick = {
                                navController.navigateAndClear(Routes.Main)
                            }
                        ) {
                            Text("Go to main")
                        }
                    }
                }
            }

            screen<Routes.Main> {
                MainUI.Screen()
            }
        }
    }
}