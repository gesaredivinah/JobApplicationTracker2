package com.Gesare.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface JobApplicationDao {
    @Query("SELECT * FROM job_application")
    fun getAll(): LiveData<List<JobApplication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jobApplication: JobApplication)
}

