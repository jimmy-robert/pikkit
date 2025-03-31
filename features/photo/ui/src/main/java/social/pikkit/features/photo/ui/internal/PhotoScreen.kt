package social.pikkit.features.photo.ui.internal

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import social.pikkit.core.ui.PikkitUI
import social.pikkit.core.ui.navigation.Navigation
import social.pikkit.core.ui.navigation.navigateAndClear
import social.pikkit.core.ui.navigation.screen
import social.pikkit.core.ui.wip.WorkInProgress
import social.pikkit.features.photo.ui.internal.camera.CameraScreen
import social.pikkit.features.photo.ui.internal.confirmation.ConfirmationScreen
import social.pikkit.features.photo.ui.internal.success.SuccessScreen

private object Routes {
    @Serializable
    data object Home

    @Serializable
    data class Validation(val filePath: String)

    @Serializable
    data object Success
}

@Composable
internal fun PhotoScreen(
    onFinish: () -> Unit,
    onCancel: () -> Unit
) {
    PikkitUI.Navigation(
        startDestination = Routes.Home
    ) {
        screen<Routes.Home> {
            CameraScreen(
                onCapture = { navController.navigate(Routes.Validation(it)) },
                onCancel = { onCancel() }
            )
        }

        screen<Routes.Validation> {
            ConfirmationScreen(
                filePath = route.filePath,
                onConfirm = { navController.navigateAndClear(Routes.Success) },
                onRetry = { navController.popBackStack() }
            )
        }

        screen<Routes.Success> {
            SuccessScreen(
                onClose = onFinish
            )
        }
    }
}