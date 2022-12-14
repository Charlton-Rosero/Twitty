package com.example.lib_data.data

import com.example.lib_data.domain.models.Comment
import com.example.lib_data.domain.models.Post
import com.example.lib_data.domain.models.PostDelete
import com.example.lib_data.domain.models.PostModel
import com.example.lib_data.domain.models.PostUpdate
import com.example.lib_data.domain.models.Token
import com.example.lib_data.domain.models.User
import com.example.lib_data.util.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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

    @GET("posts/{id}/comments")
    suspend fun getComment(@Header("Authorization") token: String, @Path("id") id: Long): Response<List<Comment>>


    @GET("posts/{id}")
    suspend fun getPostById(@Header("Authorization") token: String, @Path("id") id: Long): Response<Post>

    @POST("post/comment")
    suspend fun createComment(@Header("Authorization") token: String, @Body comment: Comment): Response<Comment>


    @HTTP(method = "DELETE", path = "post", hasBody = true)
    suspend fun deletePost(@Header("Authorization") token: String, @Body post: PostDelete):Response<Any>

    @HTTP(method = "PATCH", path = "post", hasBody = true)
    suspend fun updatePost(@Header("Authorization") token: String, @Body post: PostUpdate):Response<Any>


}
