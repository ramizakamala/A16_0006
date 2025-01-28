package com.example.final_project.di

import com.example.final_project.repository.KategoriRepository
import com.example.final_project.repository.NetworkKategoriRepository
import com.example.final_project.service.KategoriService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface KategoriContainer {
    val kategoriRepository: KategoriRepository
}

class KateContainer : KategoriContainer {

    private val baseUrl = "http://10.0.2.2:3000/api/kategori/" //localhost diganti ip kalo run di hp

    private val json = Json { ignoreUnknownKeys = true}

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()
    private val kategoriService: KategoriService by lazy {
        retrofit.create(KategoriService::class.java)
    }
    override val kategoriRepository: KategoriRepository by lazy {
        NetworkKategoriRepository(kategoriService)
    }
}