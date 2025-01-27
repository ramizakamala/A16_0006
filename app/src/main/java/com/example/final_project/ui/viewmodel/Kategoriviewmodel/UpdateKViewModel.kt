package com.example.final_project.ui.viewmodel.Kategoriviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Kategori
import com.example.final_project.repository.KategoriRepository
import kotlinx.coroutines.launch
import okio.IOException

class UpdateKViewModel(private val kat: KategoriRepository) : ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent){
        UpdateuiState = UpdateUiState(updateuiEvent = updateUiEvent)

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadkategori(idkategori: String) {
        viewModelScope.launch {
            try {
                val kategori = kat.getKategoriById(idkategori)
                UpdateuiState = kategori.toUpdateUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateKat( ){
        viewModelScope.launch {
            try {
                val kategori= UpdateuiState.updateuiEvent.toKat()
                kat.updateKategori(kategori.idkategori, kategori)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }
}

data class UpdateUiState(
    val updateuiEvent: UpdateUiEvent = UpdateUiEvent()
)

data class UpdateUiEvent(
    val idkategori: String = "",
    val namakategori: String = ""
)

fun UpdateUiEvent.toKat(): Kategori = Kategori(
    idkategori = idkategori,
    namakategori = namakategori
)

fun Kategori.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateuiEvent = toUpdateUiEvent()
)

fun Kategori.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idkategori = idkategori,
    namakategori = namakategori
)