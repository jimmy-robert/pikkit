package social.pikkit.features.settings.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import social.pikkit.features.settings.data.SettingsDataModule
import social.pikkit.features.settings.ui.SettingsUIModule

@Module(includes = [SettingsDataModule::class, SettingsUIModule::class])
@ComponentScan
class SettingsModule