package com.example.lib_data.repository

import com.example.lib_data.domain.models.Token
import com.example.lib_data.domain.models.User
import com.example.lib_data.util.Resource

/**
 * Repository.
 *
 * @constructor Create empty Repository.
 */
interface Repository {
    /**
     */
    suspend fun loginUser(username: String, password: String): Resource<Token>

    /**
     *
     */
    suspend fun signUp(user: User): Resource<User>
}
