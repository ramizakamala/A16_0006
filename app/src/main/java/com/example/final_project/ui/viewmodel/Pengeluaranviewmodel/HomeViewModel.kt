package com.example.final_project.ui.viewmodel.Pengeluaranviewmodel

import android.util.Log
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

sealed class HomeUiState{
    data class Success(val pengeluaran: List<Pengeluaran>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel(private val peng: PengeluaranRepository) : ViewModel() {
    var pengUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPeng()
    }

    fun getPeng() {
        viewModelScope.launch {
            pengUiState = HomeUiState.Loading
            pengUiState = try {
                HomeUiState.Success(peng.getPengeluaran().data)
            } catch (e: IOException) {
                // Log error untuk IOException
                Log.e("GetPengError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                // Log error untuk HttpException
                Log.e("GetPengError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }


    fun deletePeng(idpengeluaran: String){
        viewModelScope.launch {
            try {
                peng.deletePengeluaran(idpengeluaran)
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}