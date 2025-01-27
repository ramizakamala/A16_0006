package com.example.final_project.ui.viewmodel.Pendapatanviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Pendapatan
import com.example.final_project.repository.PendapatanRepository
import kotlinx.coroutines.launch
import okio.IOException

class UpdatePViewModel(private val pend: PendapatanRepository) : ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent){
        UpdateuiState = UpdateUiState(updateuiEvent = updateUiEvent)

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadPendapatan(idpendapatan: String) {
        viewModelScope.launch {
            try {
                val pendapatan = pend.getPendapatanById(idpendapatan)
                UpdateuiState = pendapatan.toUpdateUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updatePend( ){
        viewModelScope.launch {
            try {
                val pendapatan = UpdateuiState.updateuiEvent.toPend()
                pend.updatePendapatan(pendapatan.idpendapatan, pendapatan)
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
    val idpendapatan: String = "",
    val idaset: String = "",
    val idkategori: String = "",
    val tanggaltransaksi: String = "",
    val total: String = "",
    val catatan: String = ""
)

fun UpdateUiEvent.toPend(): Pendapatan = Pendapatan(
    idpendapatan = idpendapatan,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)

fun Pendapatan.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateuiEvent = toUpdateUiEvent()
)

fun Pendapatan.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idpendapatan = idpendapatan,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)