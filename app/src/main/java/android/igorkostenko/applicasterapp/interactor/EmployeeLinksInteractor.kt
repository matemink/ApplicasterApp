package android.igorkostenko.applicasterapp.interactor

import android.igorkostenko.applicasterapp.model.Entry
import android.igorkostenko.applicasterapp.network.ApiResponse
import android.igorkostenko.applicasterapp.network.ApplicasterClient
import android.igorkostenko.applicasterapp.network.message
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeLinksInteractor(private val applicasterClient: ApplicasterClient) {

    suspend fun loadApplicasterEmployeeLinks(): MutableLiveData<EmployeeEntriesState> {
        val liveData =
            MutableLiveData<EmployeeEntriesState>().apply { EmployeeEntriesState.Loading }
        withContext(Dispatchers.IO) {
            applicasterClient.fetchEmployeesLinks { response ->
                liveData.postValue(
                    when (response) {
                        is ApiResponse.Success -> EmployeeEntriesState.Success(
                            response.data?.entry ?: listOf()
                        )
                        is ApiResponse.Failure.Error -> EmployeeEntriesState.Error(response.message())
                        is ApiResponse.Failure.Exception -> EmployeeEntriesState.Error(response.message())
                    }
                )
            }
        }
        return liveData
    }

    sealed class EmployeeEntriesState {
        data class Success(val entryList: List<Entry>) : EmployeeEntriesState()
        object Loading : EmployeeEntriesState()
        data class Error(val error: String) : EmployeeEntriesState()
    }
}
