package com.Gesare.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_application")
data class JobApplication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val companyName: String,
    val applicantName: String,
    val interviewDate: String,
    val followUpDate: String
)

