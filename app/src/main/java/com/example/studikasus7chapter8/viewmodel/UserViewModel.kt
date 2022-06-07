package com.example.studikasus7chapter8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studikasus7chapter8.model.GetAllUserResponseItem
import com.example.studikasus7chapter8.model.PostUser
import com.example.studikasus7chapter8.model.RequestUser
import com.example.studikasus7chapter8.networking.ApiServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(apiServices: ApiServices) : ViewModel() {
    private val userState = MutableStateFlow(emptyList<GetAllUserResponseItem>())
    val dataUserState: StateFlow<List<GetAllUserResponseItem>> get() = userState
    private val api = apiServices

    init {
        viewModelScope.launch {
            val dataUser = apiServices.getAllUser()
            userState.value = dataUser
        }
    }

    fun insertNewUser(requestUser: RequestUser): Boolean {
        var messageResponse = false
        viewModelScope.launch {
            api.addNewUser(requestUser)
                .enqueue(object : Callback<PostUser> {
                    override fun onResponse(
                        call: Call<PostUser>,
                        response: Response<PostUser>
                    ) {
                        messageResponse = response.isSuccessful
                    }

                    override fun onFailure(call: Call<PostUser>, t: Throwable) {
                        messageResponse = false
                    }

                })
        }
        return messageResponse
    }
}