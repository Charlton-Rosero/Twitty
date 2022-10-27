package com.example.lib_data.data

import com.example.lib_data.domain.models.Post
import com.example.lib_data.domain.models.PostModel
import com.example.lib_data.domain.models.Token
import com.example.lib_data.domain.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Api service.
 *
 * @constructor Create empty Api service.
 */
interface ApiService {

    @POST("login")
    suspend fun loginUser(@Query("username") username: String, @Query("password") password: String): Response<Token>

    @POST("users/save")
    suspend fun signUp(@Body user: User): Response<User>

    @GET("posts")
    suspend fun getPost(@Header("Authorization") token: String):Response<List<Post>>

    @POST("post")
    suspend fun createPost(@Header("Authorization") token: String, @Body post:PostModel): Response<Post>


}
