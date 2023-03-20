package com.grandstream.sunflower.repository

import com.grandstream.sunflower.api.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun getUserSearchResult() = userService.searchUsers()
}