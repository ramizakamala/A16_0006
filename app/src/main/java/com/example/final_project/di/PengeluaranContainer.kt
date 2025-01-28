package com.example.final_project.di

import com.example.final_project.repository.NetworkPengeluaranRepository
import com.example.final_project.repository.PengeluaranRepository
import com.example.final_project.service.PengeluaranService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface PengeluaranContainer {
    val pengeluaranRepository: PengeluaranRepository
}

class PengContainer : PengeluaranContainer {

    private val baseUrl = "http://10.0.2.2:3000/api/pengeluaran/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()
    private val pengeluaranService: PengeluaranService by lazy {
        retrofit.create(PengeluaranService::class.java)
    }
    override val pengeluaranRepository: PengeluaranRepository by lazy {
        NetworkPengeluaranRepository(pengeluaranService)
    }
}