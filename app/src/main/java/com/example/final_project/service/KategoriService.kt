package com.example.final_project.service

import com.example.final_project.model.AllKategoriResponse
import com.example.final_project.model.Kategori
import com.example.final_project.model.KategoriDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("store")
    suspend fun insertKategori( @Body kategori: Kategori)

    @GET(".")
    suspend fun getAllKategori(): AllKategoriResponse

    @GET("idkategori")
    suspend fun getKategoriById(@Path("idkategori")idkategori: String): KategoriDetailResponse

    @PUT("idkategori")
    suspend fun updateKategori(@Path("idkategori")idkategori: String, @Body kategori: Kategori)

    @DELETE("idkategori")
    suspend fun deleteKategori(@Path("idkategori")idkategori: String):retrofit2.Response<Void>

}