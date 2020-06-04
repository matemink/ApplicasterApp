package android.igorkostenko.applicasterapp.network

import android.igorkostenko.applicasterapp.model.EmployeeEntries
import retrofit2.Call
import retrofit2.http.GET

interface ApplicasterService {

    @GET("link_json.json")
    fun fetchEmployeesLinkList(): Call<EmployeeEntries>

    @GET("video_json.json")
    fun fetchEmployeesVideoList(): Call<EmployeeEntries>
}
