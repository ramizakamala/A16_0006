package com.example.final_project.repository

import com.example.final_project.model.AllKategoriResponse
import com.example.final_project.model.Kategori
import com.example.final_project.service.KategoriService
import okio.IOException

interface KategoriRepository {
    suspend fun insertKategori(kategori: Kategori)

    suspend fun getKategori(): AllKategoriResponse

    suspend fun updateKategori(idkategori: String, kategori: Kategori)

    suspend fun deleteKategori(idkategori: String)

    suspend fun getKategoriById(idkategori: String): Kategori
}

class NetworkKategoriRepository(
    private val kategoriApiService: KategoriService
) : KategoriRepository {

    override suspend fun insertKategori(kategori: Kategori) {
        kategoriApiService.insertKategori(kategori)
    }

    override suspend fun updateKategori(idkategori: String, kategori: Kategori) {
        kategoriApiService.updateKategori(idkategori, kategori)
    }

    override suspend fun getKategori(): AllKategoriResponse {
        return kategoriApiService.getAllKategori()
    }

    override suspend fun deleteKategori(idkategori: String) {
        try {
            val reponse = kategoriApiService.deleteKategori(idkategori)
            if (!reponse.isSuccessful) {
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${reponse.code()}")
            } else {
                reponse.message()
                println(reponse.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKategoriById(idkategori: String): Kategori {
        return kategoriApiService.getKategoriById(idkategori).data
    }
}