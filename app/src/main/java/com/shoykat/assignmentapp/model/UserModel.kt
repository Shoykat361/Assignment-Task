package com.shoykat.assignmentapp.model


import com.google.gson.annotations.SerializedName
import com.shoykat.assignmentapp.model.Data

data class UserModel(
    @SerializedName("data")
    val userData: Data?,
    @SerializedName("status")
    val status: Boolean?
)