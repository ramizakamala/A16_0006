package com.example.final_project.service

import com.example.final_project.model.AllPengeluaranResponse
import com.example.final_project.model.Pengeluaran
import com.example.final_project.model.PengeluaranDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PengeluaranService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertPengeluaran( @Body pengeluaran: Pengeluaran)

    @GET(".")
    suspend fun getAllPengeluaran(): AllPengeluaranResponse

    @GET("idpengeluaran")
    suspend fun getPengeluaranById(@Path("idpengeluaran")idpengeluaran: String): PengeluaranDetailResponse

    @PUT("idpengeluaran")
    suspend fun updatePengeluaran(@Path("idpengeluaran")idpengeluaran: String, @Body pengeluaran: Pengeluaran)

    @DELETE("idpengeluaran")
    suspend fun deletePengeluaran(@Path("idpengeluaran")idpengeluaran: String):retrofit2.Response<Void>
}