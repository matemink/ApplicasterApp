package android.igorkostenko.applicasterapp

import android.app.Application
import android.igorkostenko.applicasterapp.di.interactorModule
import android.igorkostenko.applicasterapp.di.networkModule
import android.igorkostenko.applicasterapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicasterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicasterApp)
            modules(networkModule)
            modules(interactorModule)
            modules(viewModelModule)
        }
    }
}