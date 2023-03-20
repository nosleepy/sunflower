package com.grandstream.sunflower.api

import com.grandstream.sunflower.data.UserSearchResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserService {
    @GET("search/users")
    suspend fun searchUsers(): UserSearchResponse

    companion object {
        private const val BASE_URL = "https://getman.cn/mock/"

        fun create(): UserService {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
    }
}