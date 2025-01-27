package com.example.final_project.repository

import com.example.final_project.model.AllPengeluaranResponse
import com.example.final_project.model.Pengeluaran
import com.example.final_project.service.PengeluaranService
import okio.IOException

interface PengeluaranRepository {
    suspend fun insertPengeluaran(pengeluaran: Pengeluaran)

    suspend fun getPengeluaran(): AllPengeluaranResponse

    suspend fun updatePengeluaran(idpengeluaran: String, pengeluaran: Pengeluaran)

    suspend fun deletePengeluaran(idpengeluaran: String)

    suspend fun getPengeluaranById(idpengeluaran: String): Pengeluaran
}

class NetworkPengeluaranRepository(
    private val pengeluaranApiService: PengeluaranService
) : PengeluaranRepository {

    override suspend fun insertPengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranApiService.insertPengeluaran(pengeluaran)
    }

    override suspend fun updatePengeluaran(idpengeluaran: String, pengeluaran: Pengeluaran) {
        pengeluaranApiService.updatePengeluaran(idpengeluaran, pengeluaran)
    }

    override suspend fun getPengeluaran(): AllPengeluaranResponse {
        return pengeluaranApiService.getAllPengeluaran()
    }

    override suspend fun deletePengeluaran(idpengeluaran: String) {
        try {
            val reponse = pengeluaranApiService.deletePengeluaran(idpengeluaran)
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

    override suspend fun getPengeluaranById(idpengeluaran: String): Pengeluaran {
        return pengeluaranApiService.getPengeluaranById(idpengeluaran).data
    }
}