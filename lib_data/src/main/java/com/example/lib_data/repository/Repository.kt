package com.example.lib_data.repository

import com.example.lib_data.domain.models.Comment
import com.example.lib_data.domain.models.Post
import com.example.lib_data.domain.models.PostModel
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
    /**
     *
     */
    suspend fun getPost(token: String): Resource<List<Post>>
    /**
     *
     */
    suspend fun createPost(token: String, post: PostModel): Resource<Post>
    /**
     *
     */
    suspend fun getComment(token: String, id: Long): Resource<List<Comment>>
    /**
     *
     */
    suspend fun getPostById(token: String, id: Long): Resource<Post>

}
