package android.igorkostenko.applicasterapp.di

import android.igorkostenko.applicasterapp.interactor.EmployeeLinksInteractor
import android.igorkostenko.applicasterapp.interactor.EmployeeVideosInteractor
import org.koin.dsl.module

val interactorModule = module {

    single { EmployeeLinksInteractor(get()) }

    single { EmployeeVideosInteractor(get()) }
}
