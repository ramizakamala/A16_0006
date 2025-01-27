package com.example.final_project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aset (

    @SerialName(value = "id_aset")
    val idaset: String,
    @SerialName(value = "nama_aset")
    val namaaset: String
)

@Serializable
data class AllAsetResponse(
    val status: Boolean,
    val message: String,
    val data: List<Aset>
)

@Serializable
data class AsetDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Aset
)
