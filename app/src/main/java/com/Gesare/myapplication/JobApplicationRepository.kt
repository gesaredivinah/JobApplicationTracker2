package com.Gesare.myapplication

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JobApplicationRepository(private val dao: JobApplicationDao) {
    val allJobApplications: LiveData<List<JobApplication>> = dao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(jobApplication: JobApplication) {
        dao.insert(jobApplication)
    }
}

class JobApplicationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JobApplicationRepository
    val allJobApplications: LiveData<List<JobApplication>>

    init {
        val jobApplicationDao = AppDatabase.getDatabase(application).jobApplicationDao()
        repository = JobApplicationRepository(jobApplicationDao)
        allJobApplications = repository.allJobApplications
    }

    fun insert(jobApplication: JobApplication) = viewModelScope.launch {
        repository.insert(jobApplication)
    }
}
