package com.example.final_project.ui.viewmodel.Pengeluaranviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.final_project.model.Pengeluaran
import com.example.final_project.repository.PengeluaranRepository
import kotlinx.coroutines.launch
import okio.IOException

class UpdateViewModel(private val peng: PengeluaranRepository) : ViewModel() {
    var UpdateUiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent) {
        UpdateUiState = UpdateUiState(updateuiEvent = updateUiEvent)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadPengeluaran(idpengeluaran: String) {
        viewModelScope.launch {
            try {
                val pengeluaran = peng.getPengeluaranById(idpengeluaran)
                UpdateUiState = pengeluaran.toUpdateUiState()
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updatePeng() {
        viewModelScope.launch {
            try {
                val pengeluaran = UpdateUiState.updateuiEvent.toPeng()
                peng.updatePengeluaran(pengeluaran.idpengeluaran, pengeluaran)
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
    val idpengeluaran: String = "",
    val idaset: String = "",
    val idkategori: String = "",
    val tanggaltransaksi: String = "",
    val total: String = "",
    val catatan: String = ""
)

fun UpdateUiEvent.toPeng(): Pengeluaran = Pengeluaran(
    idpengeluaran = idpengeluaran,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)

fun Pengeluaran.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateuiEvent = toUpdateUiEvent()
)

fun Pengeluaran.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idpengeluaran = idpengeluaran,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)