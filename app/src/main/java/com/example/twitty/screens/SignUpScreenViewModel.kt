package com.example.twitty.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.domain.models.User
import com.example.lib_data.repository.Repository
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
*
 */
@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repo: Repository
): ViewModel(){
    private val _sign: MutableStateFlow<Resource<User>?> = MutableStateFlow(null)
    val sign = _sign.asStateFlow()

    /**
     *
     */
    fun signUP(user:User){
        viewModelScope.launch {
            _sign.value = repo.signUp(user)
            when(_sign.value){
                is Resource.Error -> println("ERROR!!!")
                Resource.Loading -> println("Loading..")
                is Resource.Success -> println("got em ${_sign.value}")
                null -> {}
            }
        }
    }

}
