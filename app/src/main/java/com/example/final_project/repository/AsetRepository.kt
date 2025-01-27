package com.example.final_project.repository

import com.example.final_project.model.AllAsetResponse
import com.example.final_project.model.Aset
import com.example.final_project.service.AsetService
import okio.IOException

interface AsetRepository {
    suspend fun insertAset(aset: Aset)

    suspend fun getAset(): AllAsetResponse

    suspend fun updateAset(idaset: String, aset: Aset)

    suspend fun deleteAset(idaset: String)

    suspend fun getAsetById(idaset: String): Aset
}

class NetworkAsetRepository(
    private val asetApiService: AsetService
) : AsetRepository {

    override suspend fun insertAset(aset: Aset) {
        asetApiService.insertAset(aset)
    }

    override suspend fun updateAset(idaset: String, aset: Aset) {
        asetApiService.updateAset(idaset, aset)
    }

    override suspend fun getAset(): AllAsetResponse {
        return asetApiService.getAllAset()
    }

    override suspend fun deleteAset(idaset: String) {
        try {
            val reponse = asetApiService.deleteAset(idaset)
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

    override suspend fun getAsetById(idaset: String): Aset {
        return asetApiService.getAsetById(idaset).data
    }
}