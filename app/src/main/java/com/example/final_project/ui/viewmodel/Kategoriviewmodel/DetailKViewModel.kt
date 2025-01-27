package com.example.final_project.ui.viewmodel.Kategoriviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Kategori
import com.example.final_project.repository.KategoriRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState{
    data class Success(val kategori: Kategori) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailKViewModel(private val kat: KategoriRepository) :ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: MutableStateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getKategoriById(idkategori: String){
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                DetailUiState.Success(kat.getKategoriById(idkategori))
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateKat(idkategori: String, updatedKategori: Kategori){
        viewModelScope.launch {
            try {
                kat.updateKategori(idkategori, updatedKategori)
                _detailUiState.value = DetailUiState.Success(updatedKategori)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteKat(idkategori: String){
        viewModelScope.launch {
            try {
                kat.deleteKategori(idkategori)
                _detailUiState.value = DetailUiState.Loading
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }

    }
}