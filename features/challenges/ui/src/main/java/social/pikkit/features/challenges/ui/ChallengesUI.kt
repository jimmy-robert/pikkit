package social.pikkit.features.challenges.ui

import androidx.compose.runtime.Composable
import social.pikkit.features.challenges.ui.internal.ChallengesScreen

object ChallengesUI {
    @Composable
    fun Screen(
        onStart: () -> Unit
    ) = ChallengesScreen(onStart = onStart)
}

