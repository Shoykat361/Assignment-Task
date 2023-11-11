package com.shoykat.assignmentapp.repos

import com.shoykat.assignmentapp.Network.NetworkService
import com.shoykat.assignmentapp.model.UserModel

class UserRepository {
    suspend fun fetchUserData(): UserModel {
        val endUrl = "leaderboard.json"
        return NetworkService.userServiceApi.getUserData(endUrl)
    }
}