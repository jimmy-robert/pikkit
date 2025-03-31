package social.pikkit.features.settings.ui

import androidx.compose.runtime.Composable
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import social.pikkit.features.settings.ui.internal.SettingsScreen

object SettingsUI {
    @Composable
    fun Screen() = SettingsScreen()
}

@Module
@ComponentScan
class SettingsUIModule