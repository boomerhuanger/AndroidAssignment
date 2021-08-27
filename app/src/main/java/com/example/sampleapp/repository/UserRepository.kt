package com.example.sampleapp.repository

import android.util.Log
import com.example.sampleapp.models.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.MutableLiveData;
import com.example.sampleapp.network.NetworkFactory
import com.example.sampleapp.network.UserService


class UserRepository {
    private val networkFactory = NetworkFactory()
    private val service = networkFactory.getNetworkService()
    private var usersList = MutableLiveData<List<User>>()
    fun getUsers() {
        service.users().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                usersList.value = response.body()
                Log.i("User data", usersList.value.toString())
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable){
                Log.i("error message", t.message.toString())
                usersList.value = null
            }
        })
    }
    fun getUsersLiveData()  = usersList
}
