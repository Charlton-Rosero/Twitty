package com.example.twitty.screens

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.domain.models.Token
import com.example.lib_data.domain.models.User
import com.example.lib_data.domain.models.datastore.DataSource
import com.example.lib_data.repository.Repository
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Login screen view model.
 */
@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repo: Repository,
    private val store: DataSource
) :ViewModel() {
  private val _user: MutableStateFlow<Resource<Token>?> = MutableStateFlow(null)

    val user = _user.asStateFlow()
    /**
     */
    @SuppressLint("SuspiciousIndentation")
    fun loginUser(username: String, password: String){
        viewModelScope.launch {
          _user.value = repo.loginUser(username, password)
            when(_user.value){
                is Resource.Error -> println("ERROR!!!")
                Resource.Loading -> println("Loading..")
                is Resource.Success -> {
                    store.setDataStore((_user.value as Resource.Success<Token>).data.accessToken)
                    store.setUser(username)
                }
                null -> {}
            }
        }

    }

}
