package social.pikkit.features.settings

import kotlinx.coroutines.flow.StateFlow

object Settings {
    enum class Theme {
        Light,
        Dark,
        System,
    }

    interface GetThemeStateUseCase {
        operator fun invoke(): StateFlow<Theme>
    }

    interface SetThemeUseCase {
        operator fun invoke(theme: Theme)
    }
}