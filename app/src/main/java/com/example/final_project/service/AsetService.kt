package com.example.final_project.service

import com.example.final_project.model.AllAsetResponse
import com.example.final_project.model.Aset
import com.example.final_project.model.AsetDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AsetService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("store")
    suspend fun insertAset( @Body aset: Aset)

    @GET(".")
    suspend fun getAllAset(): AllAsetResponse

    @GET("idaset")
    suspend fun getAsetById(@Path("idaset")idaset: String): AsetDetailResponse

    @PUT("idaset")
    suspend fun updateAset(@Path("idaset")idaset: String, @Body aset: Aset)

    @DELETE("idaset")
    suspend fun deleteAset(@Path("idaset")idaset: String):retrofit2.Response<Void>
}