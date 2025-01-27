package com.example.final_project.ui.viewmodel.Asetviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Aset
import com.example.final_project.repository.AsetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState{
    data class Success(val aset: Aset) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailAviewModel(private val set: AsetRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: MutableStateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getAsetById(idaset: String){
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                DetailUiState.Success(set.getAsetById(idaset))
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateSet(idaset: String, updatedAset: Aset){
        viewModelScope.launch {
            try {
                set.updateAset(idaset, updatedAset)
                _detailUiState.value = DetailUiState.Success(updatedAset)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteSet(idaset: String){
        viewModelScope.launch {
            try {
                set.deleteAset(idaset)
                _detailUiState.value = DetailUiState.Loading
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }

    }
}