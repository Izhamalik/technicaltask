package com.dailydriver.islamicapp.repositories


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.izhar.technical.maximus.remote.ApiResponse
import com.izhar.technical.maximus.remote.FactRetroService

import com.izhar.technical.maximus.repository.ResponseRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResponseRepositoryImp(
   private val retro: FactRetroService
) : ResponseRepository {
    var loginlivedata = MutableLiveData<ApiResponse>()
    override fun factApiCalling(fact:String
    ): MutableLiveData<ApiResponse> {
        retro.getApiResponse(fact)
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    loginlivedata.postValue(response.body())
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("Message" , t.message.toString())
                    t.message
                }
            })
        return loginlivedata
    }

}