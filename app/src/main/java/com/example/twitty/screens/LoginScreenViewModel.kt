package com.example.twitty.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Token
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Login screen view model.
 *
 * @property repo
 * @constructor Create empty Login screen view model.
 */

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repo: RepositoryImpl
) :ViewModel() {
  private val _user: MutableStateFlow<Resource<Token>?> = MutableStateFlow(null)

    val user = _user.asStateFlow()
    /**
     */
    fun loginUser(username: String, password: String){
        viewModelScope.launch {
          _user.value = repo.loginUser(username, password)
        }

    }

}
