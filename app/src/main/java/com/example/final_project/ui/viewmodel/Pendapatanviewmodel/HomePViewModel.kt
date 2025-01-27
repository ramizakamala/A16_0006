package com.example.final_project.ui.viewmodel.Pendapatanviewmodel

import android.net.http.HttpException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.model.Pendapatan
import com.example.final_project.repository.PendapatanRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val pendapatan: List<Pendapatan>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePViewModel(private val pend: PendapatanRepository) : ViewModel() {
    var pendUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPend()
    }

    fun getPend() {
        viewModelScope.launch {
            pendUiState = HomeUiState.Loading
            pendUiState = try {
                HomeUiState.Success(pend.getPendapatan().data)
            } catch (e: IOException) {
                // Log error untuk IOException
                Log.e("GetMhsError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                // Log error untuk HttpException
                Log.e("GetMhsError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }

    fun deletePend(idpendapatan: String){
        viewModelScope.launch {
            try {
                pend.deletePendapatan(idpendapatan)
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}
