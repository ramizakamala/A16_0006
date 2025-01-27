package com.example.final_project.di

import com.example.final_project.repository.NetworkPendapatanRepository
import com.example.final_project.repository.PendapatanRepository
import com.example.final_project.service.PendapatanService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface PendapatanContainer {
    val pendapatanRepository: PendapatanRepository
}

class PendContainer : PendapatanContainer {

    private val baseUrl = "http://10.0.2.2:3000/api/mahasiswa/" //localhost diganti ip kalo run di hp

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val PendapatanService: PendapatanService by lazy {
        retrofit.create(PendapatanService::class.java)
    }

    override val pendapatanRepository: PendapatanRepository by lazy {
        NetworkPendapatanRepository(PendapatanService)
    }
}