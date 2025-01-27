package com.example.final_project.ui.viewmodel.Pendapatanviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.model.Pendapatan
import com.example.final_project.repository.PendapatanRepository
import kotlinx.coroutines.launch

class InsertPViewModel(private val pend: PendapatanRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPendState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(InsertUiEvent = insertUiEvent)
    }

    suspend fun insertPend(){
        viewModelScope.launch{
            try {
                pend.insertPendapatan(uiState.InsertUiEvent.toPendapatan())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val InsertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idpendapatan : String = "",
    val idaset : String = "",
    val idkategori : String = "",
    val tanggaltransaksi : String = "",
    val total : String = "",
    val catatan : String = ""
)

fun InsertUiEvent.toPendapatan(): Pendapatan = Pendapatan(
    idpendapatan = idpendapatan,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)

fun Pendapatan.toUiStatePend(): InsertUiState = InsertUiState(
    InsertUiEvent = toInsertUiEvent()
)

fun Pendapatan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idpendapatan = idpendapatan,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)