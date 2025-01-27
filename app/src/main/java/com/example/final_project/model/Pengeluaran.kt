package com.example.final_project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class Pengeluaran (

    @SerialName(value = "id_pengeluaran")
    val idpengeluaran: String,
    @SerialName(value = "id_aset")
    val idaset: String,
    @SerialName(value = "id_kategori")
    val idkategori: String,
    @SerialName(value = "tanggal_transaksi")
    val tanggaltransaksi: String,

    val total: String,
    val catatan: String
)

@Serializable
data class AllPengeluaranResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pengeluaran>
)

@Serializable
data class PengeluaranDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pengeluaran
)