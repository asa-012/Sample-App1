package com.asa.atrae_sample_app1.main

import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val usersDataSource:UsersDataSource
    ):MainRepository {

    override suspend fun getUsersProperties(): RandomUsersDataPage {
         return usersDataSource.getUsersProperties()
    }
}