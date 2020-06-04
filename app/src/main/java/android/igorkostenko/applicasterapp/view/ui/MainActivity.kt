package android.igorkostenko.applicasterapp.view.ui

import android.igorkostenko.applicasterapp.R
import android.igorkostenko.applicasterapp.databinding.ActivityMainBinding
import android.igorkostenko.applicasterapp.interactor.EmployeeLinksInteractor
import android.igorkostenko.applicasterapp.interactor.EmployeeVideosInteractor
import android.igorkostenko.applicasterapp.view.adapter.EmployeeEntriesAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = getViewModel<MainViewModel>().apply {
            fetchEmployeeLinks()
            fetchEmployeeVideos()
        }
        val adapter = EmployeeEntriesAdapter()
        binding.apply {
            this.lifecycleOwner = this@MainActivity
            this.vm = viewModel
            this.adapter = adapter
        }

        viewModel.employeeLinksListLiveData.observe(this, Observer {
            when (it) {
                is EmployeeLinksInteractor.EmployeeEntriesState.Success -> {
                    adapter.updateList(it.entryList)
                }
                is EmployeeLinksInteractor.EmployeeEntriesState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.employeeVideosListLiveData.observe(this, Observer {
            when (it) {
                is EmployeeVideosInteractor.EmployeeEntriesState.Success -> {
                    adapter.updateList(it.entryList)
                }
                is EmployeeVideosInteractor.EmployeeEntriesState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
