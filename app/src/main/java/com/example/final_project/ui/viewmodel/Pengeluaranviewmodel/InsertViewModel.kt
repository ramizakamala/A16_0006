package com.example.final_project.ui.viewmodel.Pengeluaranviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.model.Pengeluaran
import com.example.final_project.repository.PengeluaranRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val peng: PengeluaranRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPengState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(InsertUiEvent = insertUiEvent)
    }

    suspend fun insertPeng(){
        viewModelScope.launch{
            try {
                peng.insertPengeluaran(uiState.InsertUiEvent.toPengeluaran())
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
    val idpengeluaran : String = "",
    val idaset : String = "",
    val idkategori : String = "",
    val tanggaltransaksi : String = "",
    val total : String = "",
    val catatan : String = ""
)

fun InsertUiEvent.toPengeluaran(): Pengeluaran = Pengeluaran(
    idpengeluaran = idpengeluaran,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)

fun Pengeluaran.toUiStatePeng(): InsertUiState = InsertUiState(
    InsertUiEvent = toInsertUiEvent()
)

fun Pengeluaran.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idpengeluaran = idpengeluaran,
    idaset = idaset,
    idkategori = idkategori,
    tanggaltransaksi = tanggaltransaksi,
    total = total,
    catatan = catatan
)