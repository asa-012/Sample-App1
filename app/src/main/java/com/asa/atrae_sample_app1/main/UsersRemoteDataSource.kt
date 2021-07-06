package com.asa.atrae_sample_app1.main

import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import com.asa.atrae_sample_app1.network.UsersApi

object UsersRemoteDataSource:UsersDataSource {

    //TODO DAO
    override suspend fun getUsersProperties():RandomUsersDataPage {
        return UsersApi.retrofitService.getProperties()
    }

}