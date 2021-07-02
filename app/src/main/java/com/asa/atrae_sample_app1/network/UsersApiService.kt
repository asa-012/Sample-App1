package com.asa.atrae_sample_app1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**このファイルはRepositoryからしかアクセスできない？
 *
 */

private const val BASE_URL = "https://reqres.in/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(OkHttpClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface UsersApiService {
    @GET("api/users?page=2")
    suspend fun getProperties():RandomUsersDataPage
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object UsersApi {
    val retrofitService : UsersApiService by lazy { retrofit.create(UsersApiService::class.java) }
}