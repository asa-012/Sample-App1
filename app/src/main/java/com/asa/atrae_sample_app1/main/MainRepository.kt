package com.asa.atrae_sample_app1.main

import androidx.lifecycle.LiveData
import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import com.asa.atrae_sample_app1.network.UsersApi
import com.asa.atrae_sample_app1.network.UsersProperties

interface MainRepository {

    val status:LiveData<UsersApiStatus>
    val properties:LiveData<RandomUsersDataPage>

    suspend fun getUsersProperties()

}