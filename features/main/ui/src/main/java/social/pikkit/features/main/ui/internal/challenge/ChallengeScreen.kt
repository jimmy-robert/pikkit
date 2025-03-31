package social.pikkit.features.main.ui.internal.challenge

import androidx.compose.runtime.Composable
import social.pikkit.features.photo.ui.PhotoUI

@Composable
internal fun ChallengeScreen(
    onFinish: () -> Unit,
    onCancel: () -> Unit
) {
    PhotoUI.Screen(
        onFinish = onFinish,
        onCancel = onCancel
    )
}