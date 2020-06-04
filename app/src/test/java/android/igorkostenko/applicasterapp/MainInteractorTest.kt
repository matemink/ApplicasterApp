package android.igorkostenko.applicasterapp

import android.igorkostenko.applicasterapp.ApiUtil.getCall
import android.igorkostenko.applicasterapp.interactor.EmployeeLinksInteractor
import android.igorkostenko.applicasterapp.interactor.EmployeeVideosInteractor
import android.igorkostenko.applicasterapp.network.ApplicasterClient
import android.igorkostenko.applicasterapp.network.ApplicasterService
import android.igorkostenko.applicasterapp.view.ui.main.MainViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class MainInteractorTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var employeeLinksInteractor: EmployeeLinksInteractor
    private lateinit var employeeVideosInteractor: EmployeeVideosInteractor
    private val applicasterService: ApplicasterService = mock()
    private val applicasterClient: ApplicasterClient = ApplicasterClient(applicasterService)

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        employeeLinksInteractor = EmployeeLinksInteractor(applicasterClient)
        employeeVideosInteractor = EmployeeVideosInteractor(applicasterClient)
        viewModel = MainViewModel(employeeLinksInteractor, employeeVideosInteractor)
    }

    @Test
    fun loadLinksTest(): Unit = runBlocking {
        val mockData = MockTestUtil.mockList()
        whenever(applicasterService.fetchEmployeesLinkList()).thenReturn(getCall(mockData))

        val loadData = employeeLinksInteractor.loadApplicasterEmployeeLinks()
        verify(applicasterService, atLeastOnce()).fetchEmployeesLinkList()

        val observer: Observer<EmployeeLinksInteractor.EmployeeEntriesState> = mock()
        loadData.observeForever(observer)

        val updatedData =
            EmployeeLinksInteractor.EmployeeEntriesState.Success(MockTestUtil.mockList().entry)

        loadData.postValue(updatedData)
        verify(observer).onChanged(updatedData)
        loadData.removeObserver(observer)
    }
}
