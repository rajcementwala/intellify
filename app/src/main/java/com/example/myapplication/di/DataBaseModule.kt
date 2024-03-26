package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import androidx.transition.Visibility.Mode
import com.example.myapplication.db.TaskDao
import com.example.myapplication.db.TaskDatabase
import com.example.myapplication.repository.LocalDataSource
import com.example.myapplication.utils.Contstants.TASK_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun providesTaskDao(database: TaskDatabase):TaskDao{
            return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java,
            TASK_DATABASE_NAME
        ).build()
    }


    @Provides
    fun provideApiDataSource(dao: TaskDao): LocalDataSource {
        return LocalDataSource(dao)
    }

}