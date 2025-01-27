package com.example.final_project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kategori (
    @SerialName(value = "id_kategori")
    val idkategori: String,
    @SerialName(value = "nama_kategori")
    val namakategori: String
)

@Serializable
data class AllKategoriResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kategori>
)

@Serializable
data class KategoriDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Kategori
)