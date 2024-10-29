package com.Gesare.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [JobApplication::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun jobApplicationDao(): JobApplicationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "job_application_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
