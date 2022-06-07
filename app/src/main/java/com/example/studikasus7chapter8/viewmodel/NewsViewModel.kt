package com.example.studikasus7chapter8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studikasus7chapter8.model.GetAllNewsResponseItem
import com.example.studikasus7chapter8.networking.ApiServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(apiServices: ApiServices) : ViewModel() {
    private val newsState = MutableStateFlow(emptyList<GetAllNewsResponseItem>())
    val dataNewsState: StateFlow<List<GetAllNewsResponseItem>> get() = newsState

    init {
        viewModelScope.launch {
            val dataNews = apiServices.getAllNews()
            newsState.value = dataNews
        }
    }
}