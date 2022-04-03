package com.mvvm.task.di

import android.app.Application
import com.mvvm.task.db.AppDao
import com.mvvm.task.db.AppDatabase
import com.mvvm.task.network.RetroServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseUrl = "https://reqres.in/api/"

    @Singleton
    @Provides
    fun getRetroServieInstance(retrofit: Retrofit): RetroServiceInstance {
        return retrofit.create(RetroServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getAppDB(context: Application): AppDatabase {
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): AppDao {
        return appDB.getDAO()
    }
    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}