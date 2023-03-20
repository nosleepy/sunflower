package com.grandstream.sunflower.data

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("first_name") val firstName: String,
    @field:SerializedName("last_name") val lastName: String,
    @field:SerializedName("avatar") val avatar: String
) {

}

data class UserSearchResponse(
    @field:SerializedName("results") val results: List<User>,
    @field:SerializedName("total") val total: Int
)