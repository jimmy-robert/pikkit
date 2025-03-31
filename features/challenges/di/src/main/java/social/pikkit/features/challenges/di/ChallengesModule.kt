package social.pikkit.features.challenges.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import social.pikkit.features.challenges.data.ChallengesDataModule
import social.pikkit.features.challenges.ui.ChallengesUIModule

@Module(includes = [ChallengesDataModule::class, ChallengesUIModule::class])
@ComponentScan
class ChallengesModule