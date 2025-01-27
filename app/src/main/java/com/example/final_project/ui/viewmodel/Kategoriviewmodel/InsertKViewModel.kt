package com.example.final_project.ui.viewmodel.Kategoriviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.model.Kategori
import com.example.final_project.repository.KategoriRepository
import kotlinx.coroutines.launch

class InsertKViewModel(private val kat: KategoriRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertKatState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(InsertUiEvent = insertUiEvent)
    }

    suspend fun insertKat(){
        viewModelScope.launch{
            try {
                kat.insertKategori(uiState.InsertUiEvent.toKategori())
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
    val idkategori: String = "",
    val namakategori: String = ""
)

fun InsertUiEvent.toKategori(): Kategori = Kategori(
    idkategori = idkategori,
    namakategori = namakategori
)

fun Kategori.toUiStateKat(): InsertUiState = InsertUiState(
    InsertUiEvent = toInsertUiEvent()
)

fun Kategori.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idkategori = idkategori,
    namakategori = namakategori
)