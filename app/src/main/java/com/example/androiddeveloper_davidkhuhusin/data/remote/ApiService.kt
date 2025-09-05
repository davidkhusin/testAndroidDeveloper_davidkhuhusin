package com.example.androiddeveloper_davidkhuhusin.data.remote

import com.example.androiddeveloper_davidkhuhusin.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}