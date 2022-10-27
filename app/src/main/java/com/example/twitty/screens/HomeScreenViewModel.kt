package com.example.twitty.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Post
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
class HomeScreenViewModel @Inject constructor(
    private val repo: RepositoryImpl,
    private val store: DataSource
): ViewModel() {
    private val _post: MutableStateFlow<Resource<List<Post>>?> = MutableStateFlow(null)
    val post = _post.asStateFlow()

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

}
