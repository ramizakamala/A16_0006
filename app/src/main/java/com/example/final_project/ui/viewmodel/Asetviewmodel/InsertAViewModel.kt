package com.example.final_project.ui.viewmodel.Asetviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.model.Aset
import com.example.final_project.repository.AsetRepository
import kotlinx.coroutines.launch

class InsertAViewModel(private val set: AsetRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertSetState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(InsertUiEvent = insertUiEvent)
    }

    suspend fun insertSet(){
        viewModelScope.launch{
            try {
                set.insertAset(uiState.InsertUiEvent.toAset())
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
    val idaset: String = "",
    val namaaset: String = ""
)

fun InsertUiEvent.toAset(): Aset = Aset(
    idaset = idaset,
    namaaset = namaaset
)

fun Aset.toUiStateAset(): InsertUiState = InsertUiState(
    InsertUiEvent = toInsertUiEvent()
)

fun Aset.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idaset = idaset,
    namaaset = namaaset
)