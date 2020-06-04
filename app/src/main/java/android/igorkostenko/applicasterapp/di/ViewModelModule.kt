package android.igorkostenko.applicasterapp.di

import android.igorkostenko.applicasterapp.view.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        MainViewModel(
            get(),
            get()
        )
    }
}
