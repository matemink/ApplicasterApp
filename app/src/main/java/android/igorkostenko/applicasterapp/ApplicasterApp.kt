package android.igorkostenko.applicasterapp

import android.app.Application
import android.igorkostenko.applicasterapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicasterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicasterApp)
            modules(networkModule)
        }
    }
}