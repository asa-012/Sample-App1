package com.asa.atrae_sample_app1

import com.asa.atrae_sample_app1.main.MainRepository
import com.asa.atrae_sample_app1.main.MainRepositoryImpl
import com.asa.atrae_sample_app1.main.UsersDataSource
import com.asa.atrae_sample_app1.main.UsersRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteTasksDataSource

    @Singleton
    @RemoteTasksDataSource
    @Provides
    fun provideUsersPropertiesRemoteDataSource(): UsersDataSource {
        return UsersRemoteDataSource
    }
}

    @Module
    @InstallIn(SingletonComponent::class)
    object TasksRepositoryModule {

        @Singleton
        @Provides
        fun provideMainRepository(
            @AppModule.RemoteTasksDataSource remoteTasksDataSource: UsersDataSource,
        ): MainRepository {
            return MainRepositoryImpl(
                remoteTasksDataSource
            )
        }
    }
