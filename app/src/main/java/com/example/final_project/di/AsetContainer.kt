package com.example.final_project.di

import com.example.final_project.repository.NetworkAsetRepository
import com.example.final_project.repository.AsetRepository
import com.example.final_project.service.AsetService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AsetContainer {
    val asetRepository: AsetRepository
}

class setContainer : AsetContainer {

    private val baseUrl = "http://10.0.2.2:3000/api/aset/" //localhost diganti ip kalo run di hp

    private val json = Json { ignoreUnknownKeys = true}

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val asetService: AsetService by lazy {
        retrofit.create(AsetService::class.java)
    }

    override val asetRepository : AsetRepository by lazy {
        NetworkAsetRepository(asetService)
    }
}