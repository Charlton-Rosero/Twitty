package com.example.twitty.screens.create_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Post
import com.example.lib_data.domain.models.PostModel
import com.example.lib_data.domain.models.datastore.DataSource
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 */
@HiltViewModel
class CreatePostViewModel@Inject constructor(
    private val repo: RepositoryImpl,
    private val store: DataSource
):ViewModel() {
    private val _createPost: MutableStateFlow<Resource<Post>?> = MutableStateFlow(null)

    val createPost = _createPost.asStateFlow()

    /**
     *
     */
    fun createPost(post: String){
        viewModelScope.launch {
            val token = store.getDataStore().first()
            val user = store.getUser().first()
            _createPost.value = repo.createPost("Bearer $token", PostModel(username = user, content = post))
            when(_createPost.value){
                is Resource.Error -> println("ERROR!!!")
                Resource.Loading -> println("Loading..")
                is Resource.Success -> println("got em ${_createPost.value}")
                null -> {}
            }
        }
    }

}
