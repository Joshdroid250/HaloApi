package com.example.haloapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getStatsHalo(@Url url:String): Response<ModelStatus>
}