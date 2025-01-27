package com.example.final_project.ui.viewmodel.Pengeluaranviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Pengeluaran
import com.example.final_project.repository.PengeluaranRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState {
    data class Success(val pengeluaran: Pengeluaran) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(private val peng: PengeluaranRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getPengById(idpengeluaran: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                val pengeluaran = peng.getPengeluaranById(idpengeluaran)
                DetailUiState.Success(pengeluaran)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updatePengeluaran(nim: String, updatedMahasiswa: Pengeluaran) {
        viewModelScope.launch {
            try {
               peng.updatePengeluaran(nim, updatedMahasiswa)
                _detailUiState.value = DetailUiState.Success(updatedMahasiswa)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deletePeng(idpengeluaran: String) {
        viewModelScope.launch {
            try {
                peng.deletePengeluaran(idpengeluaran)
                _detailUiState.value = DetailUiState.Loading // Optionally, you can set it to Loading or Success based on your logic
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}