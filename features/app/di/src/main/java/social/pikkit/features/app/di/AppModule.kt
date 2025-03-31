package social.pikkit.features.app.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import social.pikkit.features.app.ui.AppUIModule

@Module(includes = [AppUIModule::class])
@ComponentScan
class AppModule