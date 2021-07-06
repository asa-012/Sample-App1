package com.asa.atrae_sample_app1.main

import com.asa.atrae_sample_app1.network.RandomUsersDataPage

interface UsersDataSource {

    suspend fun getUsersProperties():RandomUsersDataPage

}