package com.asa.atrae_sample_app1.main

import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import com.asa.atrae_sample_app1.network.UsersApi
import com.asa.atrae_sample_app1.network.UsersProperties

class MainRepository {

    private val userInfo by lazy { UsersApi.retrofitService }

    suspend fun getApiService():RandomUsersDataPage{
        return userInfo.getProperties() }

}