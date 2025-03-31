package social.pikkit.features.photo.ui

import androidx.compose.runtime.Composable
import social.pikkit.features.photo.ui.internal.PhotoScreen

object PhotoUI {
    @Composable
    fun Screen(
        onFinish: () -> Unit,
        onCancel: () -> Unit
    ) = PhotoScreen(
        onFinish = onFinish,
        onCancel = onCancel
    )
}