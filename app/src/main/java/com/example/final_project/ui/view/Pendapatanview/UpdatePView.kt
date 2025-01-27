package com.example.final_project.ui.view.Pendapatanview

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.ui.customwidget.CostumeTopAppBar
import com.example.final_project.ui.navigation.DestinasiNavigasi
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.UpdatePViewModel
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.UpdateUiEvent
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.UpdateUiState
import com.example.final_project.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateP: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Mahasiswa"
    const val idpendapatan = "idpendapatan"
    val routeWithArgs = "$route/{$idpendapatan}"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePendScreen(
    pendapatan: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(pendapatan) {
        viewModel.loadPendapatan(pendapatan)
    }

    val updateUiState = viewModel.UpdateuiState

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateP.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateBody(
            updateUiState = updateUiState,
            onValueChange = viewModel::updateState,
            onUpdateClick = {
                coroutineScope.launch {
                    viewModel.updatePend()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun UpdateBody(
    updateUiState: UpdateUiState,
    onValueChange: (UpdateUiEvent) -> Unit,
    onUpdateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(26.dp)
    ) {
        UpdateFormInput(
            updateUiEvent = updateUiState.updateuiEvent,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onUpdateClick,
            shape = MaterialTheme.shapes.small,
        ) {
            Text(text = "Update")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateFormInput(
    updateUiEvent: UpdateUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateUiEvent.idpendapatan,
            onValueChange = { onValueChange(updateUiEvent.copy(idpendapatan = it)) },
            label = { Text(text = "Pendapatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.idaset,
            onValueChange = { onValueChange(updateUiEvent.copy(idaset = it)) },
            label = { Text(text = "Aset") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false, // NIM tidak boleh diubah
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.idkategori,
            onValueChange = { onValueChange(updateUiEvent.copy(idkategori = it)) },
            label = { Text(text = "kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.tanggaltransaksi,
            onValueChange = { onValueChange(updateUiEvent.copy(tanggaltransaksi = it)) },
            label = { Text(text = "tanggal transaksi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.total,
            onValueChange = { onValueChange(updateUiEvent.copy(total = it)) },
            label = { Text(text = "total") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.catatan,
            onValueChange = { onValueChange(updateUiEvent.copy(catatan = it)) },
            label = { Text(text = "Catatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}