package com.example.lib_data.data.repository

import com.example.lib_data.data.ApiService
import com.example.lib_data.domain.models.Comment
import com.example.lib_data.domain.models.Post
import com.example.lib_data.domain.models.PostModel
import com.example.lib_data.domain.models.Token
import com.example.lib_data.domain.models.User
import com.example.lib_data.repository.Repository
import com.example.lib_data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository impl.
 *
 * @property apiService
 * @constructor Create empty Repository impl.
 */
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
): Repository {
    //
    override suspend fun loginUser(username:String, password:String): Resource<Token> = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.loginUser(username,password)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN MA DUDE")
            }
        }catch (e:java.lang.Exception){
                Resource.Error(e.message.toString())
        }
    }

    override suspend fun signUp(user: User): Resource<User> = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.signUp(user)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN BRUV")
            }
        }catch (e:java.lang.Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getPost(token: String): Resource<List<Post>>  = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.getPost(token)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN")
            }
        }catch (e:java.lang.Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun createPost(token: String, post: PostModel): Resource<Post> = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.createPost(token, post)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("I AM BROKEN MAH MAAN!!")
            }
        }catch (e:java.lang.Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getComment(token: String, id: Long): Resource<List<Comment>>  = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.getComment(token, id)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("IS BROKEN MAH DUDE")
            }
        }catch (e:java.lang.Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getPostById(token: String, id: Long): Resource<Post>  = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.getPostById(token, id)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("NICE IT'S BROKEN")
            }
        }catch (e:java.lang.Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun createComment(
        token: String,
        comment: Comment
    ): Resource<Comment>  = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.createComment(token, comment)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("NICE IT'S BROKEN")
            }
        }catch (e:java.lang.Exception){
            Resource.Error(e.message.toString())
        }
    }
}
