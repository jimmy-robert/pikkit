package social.pikkit.features.settings.data.internal.theme

import android.content.Context
import androidx.core.content.edit
import org.koin.core.annotation.Singleton

@Singleton
internal class ThemePreferencesDataSource(
    context: Lazy<Context>
) {
    companion object {
        const val PREFERENCES_NAME = "data-source-theme"
        const val PREFERENCE_THEME = "current"
    }

    private val sharedPreferences by lazy {
        context.value.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun getTheme() = sharedPreferences.getString(PREFERENCE_THEME, null)
        ?.let { Theme.parse(it) }
        ?: Theme.Dark

    fun setTheme(theme: Theme) = sharedPreferences.edit {
        putString(PREFERENCE_THEME, theme.value)
    }
}