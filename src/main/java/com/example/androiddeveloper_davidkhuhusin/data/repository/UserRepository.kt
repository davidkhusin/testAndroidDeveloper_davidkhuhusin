package com.example.androiddeveloper_davidkhuhusin.data.repository

import com.example.androiddeveloper_davidkhuhusin.data.remote.ApiClient
import com.example.androiddeveloper_davidkhuhusin.model.User

class UserRepository {
    private val api = ApiClient.apiService

    suspend fun getUsers(): Result<List<User>> {
        return try {
            val response = api.getUsers()
            Result.success(response)
        }catch (e: Exception) {
            Result.failure(e)
        }
    }
}