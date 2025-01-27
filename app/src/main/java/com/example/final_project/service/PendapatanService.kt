package com.example.final_project.service

import com.example.final_project.model.AllPendapatanResponse
import com.example.final_project.model.Pendapatan
import com.example.final_project.model.PendapatanDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PendapatanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertPendapatan( @Body pendapatan: Pendapatan)

    @GET(".")
    suspend fun getAllPendapatan(): AllPendapatanResponse

    @GET("idpendapatan")
    suspend fun getPendapatanById(@Path("idpendapatan")idpendapatan: String): PendapatanDetailResponse

    @PUT("idpendapatan")
    suspend fun updatePendapatan(@Path("idpendapatan")idpendapatan: String, @Body pendapatan: Pendapatan)

    @DELETE("idpendapatan")
    suspend fun deletePendapatan(@Path("idpendapatan")idpendapatan: String):retrofit2.Response<Void>

}