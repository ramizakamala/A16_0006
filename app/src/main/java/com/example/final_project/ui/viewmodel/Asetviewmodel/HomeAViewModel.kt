package com.example.final_project.ui.viewmodel.Asetviewmodel

import android.util.Log
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

sealed class HomeUiState{
    data class Success(val aset: List<Aset>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeAViewModel(private val set: AsetRepository) : ViewModel() {
    var setUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getSet()
    }

    fun getSet(){
        viewModelScope.launch {
            setUiState = HomeUiState.Loading
            setUiState = try {
                HomeUiState.Success(set.getAset().data)
            } catch (e: IOException) {
                Log.e("GetSetError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException){
                Log.e("GetSetError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }

    fun deleteSet(idSet: String){
        viewModelScope.launch {
            try {
                set.deleteAset(idSet)
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}