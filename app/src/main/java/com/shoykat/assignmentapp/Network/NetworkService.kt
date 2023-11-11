package com.shoykat.assignmentapp.Network

import com.shoykat.assignmentapp.model.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

const val base_url = "https://raw.githubusercontent.com/mhasancse17/JsonFile/main/"


val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface UserServiceApi {
    @GET()
    suspend fun getUserData(@Url endUrl: String): UserModel

}

object NetworkService {
    val userServiceApi: UserServiceApi by lazy {
        retrofit.create(UserServiceApi::class.java)
    }
}