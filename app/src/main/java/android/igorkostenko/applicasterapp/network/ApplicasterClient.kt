package android.igorkostenko.applicasterapp.network

import android.igorkostenko.applicasterapp.model.EmployeeEntries

class ApplicasterClient(private val applicasterService: ApplicasterService) {

    fun fetchEmployeesLinks(onResult: (response: ApiResponse<EmployeeEntries>) -> Unit) {
        this.applicasterService.fetchEmployeesLinkList().transform(onResult)
    }

    fun fetchEmployeesVideos(onResult: (response: ApiResponse<EmployeeEntries>) -> Unit) {
        this.applicasterService.fetchEmployeesVideoList().transform(onResult)
    }
}
