package com.example.final_project.ui.viewmodel.Pendapatanviewmodel

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.model.Pendapatan
import com.example.final_project.repository.PendapatanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState {
    data class Success(val pendapatan: Pendapatan) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailPViewModel(private val pend: PendapatanRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getPendById(idpendapatan: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                DetailUiState.Success(pend.getPendapatanById(idpendapatan))
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
            DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updatePend(idpendapatan: String, updatedPendapatan: Pendapatan) {
        viewModelScope.launch {
            try {
                pend.updatePendapatan(idpendapatan, updatedPendapatan)
                _detailUiState.value = DetailUiState.Success(updatedPendapatan)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deletepend(idpendapatan: String) {
        viewModelScope.launch {
            try {
                pend.deletePendapatan(idpendapatan)
                _detailUiState.value = DetailUiState.Loading // Optionally, you can set it to Loading or Success based on your logic
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}

