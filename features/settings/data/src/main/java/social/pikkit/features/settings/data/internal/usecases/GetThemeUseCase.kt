package social.pikkit.features.settings.data.internal.usecases

import org.koin.core.annotation.Factory
import social.pikkit.features.settings.Settings
import social.pikkit.features.settings.data.internal.theme.ThemeRepository

@Factory
internal class GetThemeStateUseCase(
    private val themeRepository: Lazy<ThemeRepository>
) : Settings.GetThemeStateUseCase {
    override fun invoke() = themeRepository.value.theme
}

