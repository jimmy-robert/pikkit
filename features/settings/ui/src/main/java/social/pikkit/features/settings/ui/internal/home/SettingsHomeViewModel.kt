package social.pikkit.features.settings.ui.internal.home

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import social.pikkit.features.settings.Settings

@KoinViewModel
internal class SettingsHomeViewModel(
    getThemeStateUseCase: Settings.GetThemeStateUseCase,
    private val setThemeUseCase: Settings.SetThemeUseCase,
) : ViewModel() {
    val themeEntries = listOf(
        Settings.Theme.Dark,
        Settings.Theme.Light,
        Settings.Theme.System,
    )

    val themeState by lazy { getThemeStateUseCase() }

    fun setTheme(theme: Settings.Theme) = setThemeUseCase(theme)
}