package com.example.twitty.screens

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Post
import com.example.lib_data.domain.models.PostDelete
import com.example.lib_data.domain.models.User
import com.example.lib_data.domain.models.datastore.DataSource
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: RepositoryImpl,
    private val store: DataSource
): ViewModel() {
    private val _post: MutableStateFlow<Resource<List<Post>>?> = MutableStateFlow(null)
    val post = _post.asStateFlow()

    private val _del: MutableStateFlow<Any?> = MutableStateFlow(null)
    val del = _del.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init{
        getPost()
    }

    /**
     *
     *
     */
    fun getPost(){
        viewModelScope.launch {
            val token = store.getDataStore().first()
            _post.value = repo.getPost("Bearer $token")
            when(_post.value){
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success -> (_post.value as Resource.Success<List<Post>>).data
                null -> TODO()
            }
        }
    }

    fun deletePost(id:Long, user: String){
        viewModelScope.launch{
            val username = store.getUser().first()
            val token = store.getDataStore().first()
            if(user == username){
                _del.value = repo.deletePost("Bearer $token", PostDelete(id = id.toInt()))
            }
            _post.value = repo.getPost("Bearer $token")
            when(_post.value){
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success -> (_post.value as Resource.Success<List<Post>>).data
                null -> TODO()
            }
        }

    }
    fun refresh() = viewModelScope.launch {
        _isRefreshing.update { true }
        // Simulate API call
        delay(2000)
        viewModelScope.launch {
            val token = store.getDataStore().first()
            _post.value = repo.getPost("Bearer $token")
            when(_post.value){
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success -> (_post.value as Resource.Success<List<Post>>).data
                null -> TODO()
            }
        }
        _isRefreshing.update { false }
    }

}
