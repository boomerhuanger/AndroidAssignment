package com.example.sampleapp.network

import com.example.sampleapp.models.Album
import com.example.sampleapp.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/users/")
    fun users() : Call<List<User>>

    @GET("/photos/")
    fun photos() : Call<List<Album>>
}