package android.igorkostenko.applicasterapp.view.ui.main

import android.igorkostenko.applicasterapp.LiveCoroutinesViewModel
import android.igorkostenko.applicasterapp.interactor.EmployeeLinksInteractor
import android.igorkostenko.applicasterapp.interactor.EmployeeVideosInteractor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap

class MainViewModel(
    private val employeeLinksInteractor: EmployeeLinksInteractor,
    private val employeeVideosInteractor: EmployeeVideosInteractor
) : LiveCoroutinesViewModel() {
    private var linksFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val employeeLinksListLiveData: LiveData<EmployeeLinksInteractor.EmployeeEntriesState>

    private var videosFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val employeeVideosListLiveData: LiveData<EmployeeVideosInteractor.EmployeeEntriesState>

    init {
        this.employeeLinksListLiveData = this.linksFetchingLiveData.switchMap {
            launchOnViewModelScope {
                this.employeeLinksInteractor.loadApplicasterEmployeeLinks()
            }
        }

        this.employeeVideosListLiveData = this.videosFetchingLiveData.switchMap {
            launchOnViewModelScope {
                this.employeeVideosInteractor.loadApplicasterEmployeeEntries()
            }
        }
    }

    fun fetchEmployeeLinks() = this.linksFetchingLiveData.postValue(true)

    fun fetchEmployeeVideos() = this.videosFetchingLiveData.postValue(true)
}
