package tiniakovdev.com

import android.app.Application
import tiniakovdev.com.di.AppComponent
import tiniakovdev.com.di.DaggerAppComponent
import tiniakovdev.com.di.modules.DatabaseModule
import tiniakovdev.com.di.modules.DomainModule
import tiniakovdev.com.di.modules.RemoteModule


class App : Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()

    }

    companion object {
        lateinit var instance: App
            private set
    }
}
