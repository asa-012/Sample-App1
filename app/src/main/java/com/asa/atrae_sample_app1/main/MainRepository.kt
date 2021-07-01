package com.asa.atrae_sample_app1.main

import com.asa.atrae_sample_app1.network.UsersApi
import com.asa.atrae_sample_app1.network.UsersDataSample

class MainRepository {

    private val userInfo by lazy { UsersApi.retrofitService }

    suspend fun getApiService():List<UsersDataSample>{
        return userInfo.apiUsers() }

}