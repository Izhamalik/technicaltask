package com.izhar.technical.maximus.repository

import androidx.lifecycle.MutableLiveData
import com.izhar.technical.maximus.remote.ApiResponse

interface ResponseRepository {
    fun factApiCalling( fact : String
    ): MutableLiveData<ApiResponse>
}