package com.izhar.technical.maximus.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FactRetroService {

    @GET("{fact}")
    fun getApiResponse(
        @Path("fact") fact : String
    ): Call<ApiResponse>
}