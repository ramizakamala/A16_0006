package com.example.final_project.ui.viewmodel.Kategoriviewmodel

import android.util.Log
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

sealed class HomeUiState{
    data class Success(val kategori: List<Kategori>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeKViewModel(private val kat: KategoriRepository) : ViewModel() {
    var katUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getKat()
    }

    fun getKat(){
        viewModelScope.launch {
            katUiState = HomeUiState.Loading
            katUiState = try {
                HomeUiState.Success(kat.getKategori().data)
            } catch (e: IOException) {
                Log.e("GetKatError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException){
                Log.e("GetKatError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }

    fun deleteKat(idkategori: String){
        viewModelScope.launch {
            try {
                kat.deleteKategori(idkategori)
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}
