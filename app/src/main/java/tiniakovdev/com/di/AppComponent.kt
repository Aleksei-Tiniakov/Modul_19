package tiniakovdev.com.di

import dagger.Component
import tiniakovdev.com.di.modules.DatabaseModule
import tiniakovdev.com.di.modules.DomainModule
import tiniakovdev.com.di.modules.RemoteModule
import tiniakovdev.com.viewmodul.HomeFragmentViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

}