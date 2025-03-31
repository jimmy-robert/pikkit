package social.pikkit

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import social.pikkit.features.app.ui.AppUIModule
import social.pikkit.features.challenges.di.ChallengesModule
import social.pikkit.features.settings.di.SettingsModule

@Module(
    includes = [
        AppUIModule::class,
        ChallengesModule::class,
        SettingsModule::class
    ]
)
@ComponentScan
class PikkitModule