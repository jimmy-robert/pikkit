package social.pikkit.features.settings.data.internal.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Singleton
import social.pikkit.features.settings.Settings
import social.pikkit.features.settings.data.internal.theme.Theme.Companion.toTheme

@Singleton
internal class ThemeRepository(
    themePreferencesDataSource: Lazy<ThemePreferencesDataSource>
) {
    private val themePreferencesDataSource by themePreferencesDataSource

    private val mutableTheme by lazy {
        themePreferencesDataSource.value.getTheme().let { MutableStateFlow(value = it.toTheme()) }
    }

    val theme = mutableTheme.asStateFlow()

    fun setTheme(theme: Settings.Theme) {
        themePreferencesDataSource.setTheme(theme = Theme.from(theme))
        mutableTheme.value = theme
    }
}