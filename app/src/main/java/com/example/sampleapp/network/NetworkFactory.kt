package com.example.sampleapp.network

import com.example.sampleapp.models.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkFactory {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
    private var service: UserService = retrofit.create(UserService::class.java)

    fun getNetworkService() = service
}