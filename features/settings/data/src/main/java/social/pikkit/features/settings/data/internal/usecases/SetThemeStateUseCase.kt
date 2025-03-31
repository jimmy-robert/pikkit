package social.pikkit.features.settings.data.internal.usecases

import org.koin.core.annotation.Factory
import social.pikkit.features.settings.Settings
import social.pikkit.features.settings.data.internal.theme.ThemeRepository

@Factory
internal class SetThemeStateUseCase(
    private val themeRepository: Lazy<ThemeRepository>
) : Settings.SetThemeUseCase {
    override fun invoke(theme: Settings.Theme) = themeRepository.value.setTheme(theme)
}