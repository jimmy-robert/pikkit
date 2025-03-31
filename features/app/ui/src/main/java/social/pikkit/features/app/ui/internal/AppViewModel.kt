package social.pikkit.features.app.ui.internal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.annotation.KoinViewModel
import social.pikkit.features.settings.Settings

@KoinViewModel
internal class AppViewModel(
    settings: Lazy<Settings.GetThemeStateUseCase>
) : ViewModel() {
    val themeState by lazy {
        settings.value.invoke()
    }

    fun isAuthenticated() = true
}