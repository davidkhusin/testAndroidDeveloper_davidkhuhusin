package com.example.androiddeveloper_davidkhuhusin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddeveloper_davidkhuhusin.data.repository.UserRepository
import com.example.androiddeveloper_davidkhuhusin.model.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchUsers(){
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            val result = repository.getUsers()
            if(result.isSuccess){
                _users.value = result.getOrNull()
            }else{
                _error.value = result.exceptionOrNull()?.localizedMessage
            }

            _loading.value = false
        }
    }
}