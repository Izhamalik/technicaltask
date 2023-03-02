package com.izhar.technical.maximus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izhar.technical.maximus.remote.ApiResponse
import com.izhar.technical.maximus.repository.ResponseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val repo: ResponseRepository ) : ViewModel() {

    private var loginApiResponse = MutableLiveData<ApiResponse>()

    fun getResponse(fact : String): MutableLiveData<ApiResponse> {
        viewModelScope.launch {
            loginApiResponse = repo.factApiCalling(fact)
        }
        return loginApiResponse
    }
}