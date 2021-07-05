package com.asa.atrae_sample_app1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://reqres.in/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(OkHttpClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface UsersApiService {
    @GET("api/users?page=2")
    suspend fun getProperties():RandomUsersDataPage
}

object UsersApi {
    val retrofitService : UsersApiService by lazy { retrofit.create(UsersApiService::class.java) }
}