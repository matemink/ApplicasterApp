package android.igorkostenko.applicasterapp.di

import android.igorkostenko.applicasterapp.view.ui.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { MainViewModel(get(), get()) }
}
