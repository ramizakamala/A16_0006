package com.example.final_project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pendapatan (

    @SerialName(value = "id_pendapatan")
    val idpendapatan: String,
    @SerialName(value = "id_kategori")
    val idkategori: String,
    @SerialName(value = "id_aset")
    val idaset: String,
    @SerialName(value = "tanggal_transaksi")
    val tanggaltransaksi: String,

    val total: String,
    val catatan: String
)

@Serializable
data class AllPendapatanResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pendapatan>
)

@Serializable
data class PendapatanDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pendapatan
)