package com.example.final_project.ui.viewmodel.Asetviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Aset
import com.example.final_project.repository.AsetRepository
import kotlinx.coroutines.launch
import okio.IOException

class UpdateAViewModel(private val set: AsetRepository) : ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent){
        UpdateuiState = UpdateUiState(updateuiEvent = updateUiEvent)

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadAset(idaset: String) {
        viewModelScope.launch {
            try {
                val aset = set.getAsetById(idaset)
                UpdateuiState = aset.toUpdateUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateSet( ){
        viewModelScope.launch {
            try {
                val kategori= UpdateuiState.updateuiEvent.toSet()
                set.updateAset(kategori.idaset, kategori)
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
    val idaset: String = "",
    val namaaset: String = ""
)

fun UpdateUiEvent.toSet(): Aset = Aset(
    idaset = idaset,
    namaaset = namaaset
)

fun Aset.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateuiEvent = toUpdateUiEvent()
)

fun Aset.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idaset = idaset,
    namaaset = namaaset
)