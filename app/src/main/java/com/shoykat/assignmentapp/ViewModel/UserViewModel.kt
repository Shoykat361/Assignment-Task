package com.shoykat.assignmentapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoykat.assignmentapp.model.UserModel
import com.shoykat.assignmentapp.repos.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){
    val repository = UserRepository()
    val userLiveData: MutableLiveData<UserModel> = MutableLiveData()

    fun fetchUserData() : LiveData<UserModel> {
        viewModelScope.launch {
            try {
                userLiveData.value = repository.fetchUserData()
            }catch (e: Exception) {
                e.localizedMessage?.let { Log.e("UserViewModel", it) }
            }
        }
        return userLiveData
    }
}
