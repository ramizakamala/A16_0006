package com.example.final_project.repository

import com.example.final_project.model.AllPendapatanResponse
import com.example.final_project.model.Pendapatan
import com.example.final_project.service.PendapatanService
import okio.IOException

interface PendapatanRepository {
    suspend fun insertPendapatan(pendapatan: Pendapatan)

    suspend fun getPendapatan(): AllPendapatanResponse

    suspend fun updatePendapatan(idpendapatan: String, pendapatan: Pendapatan)

    suspend fun deletePendapatan(idpendapatan: String)

    suspend fun getPendapatanById(idpendapatan: String): Pendapatan
}

class NetworkPendapatanRepository(
    private val pendapatanApiService: PendapatanService
) : PendapatanRepository {

    override suspend fun insertPendapatan(pendapatan: Pendapatan) {
        pendapatanApiService.insertPendapatan(pendapatan)
    }

    override suspend fun updatePendapatan(idpendapatan: String, pendapatan: Pendapatan) {
        pendapatanApiService.insertPendapatan(pendapatan)
    }

    override suspend fun getPendapatan(): AllPendapatanResponse {
        return pendapatanApiService.getAllPendapatan()
    }

    override suspend fun deletePendapatan(idpendapatan: String) {
        try {
            val reponse = pendapatanApiService.deletePendapatan(idpendapatan)
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

    override suspend fun getPendapatanById(idpendapatan: String): Pendapatan {
        return pendapatanApiService.getPendapatanById(idpendapatan).data
    }

}