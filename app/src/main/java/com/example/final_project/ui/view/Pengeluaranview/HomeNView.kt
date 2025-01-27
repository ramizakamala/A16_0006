package com.example.final_project.ui.view.Pengeluaranview

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.R
import androidx.compose.foundation.lazy.items
import com.example.final_project.model.Pengeluaran
import com.example.final_project.ui.customwidget.CostumeTopAppBar
import com.example.final_project.ui.navigation.DestinasiNavigasi
import com.example.final_project.ui.viewmodel.Pengeluaranviewmodel.HomeUiState
import com.example.final_project.ui.viewmodel.Pengeluaranviewmodel.HomeViewModel
import com.example.final_project.ui.viewmodel.PenyediaViewModel

object DestinasiHomeN : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Pengeluaran"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNScreen(
    navigateToitemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    onUpdateClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeN.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPeng()
                },
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToitemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pengeluaran")
            }
        },
    ){ innerPadding ->
        HomeStatus(
            homeUiState = viewModel.pengUiState,
            retryAction = { viewModel.getPeng() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onUpdateClick = onUpdateClick
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Pengeluaran) -> Unit = {},
    onUpdateClick: (String) -> Unit
){
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.pengeluaran.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Pengeluaran")
                }
            } else {
                PengLayout(
                    pengeluaran = homeUiState.pengeluaran,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idpengeluaran)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.load21),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading1),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = retryAction
        ) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PengLayout(
    pengeluaran: List<Pengeluaran>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pengeluaran) -> Unit,
    onDeleteClick: (Pengeluaran) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pengeluaran) { pengeluaran ->
            PengCard(
                pengeluaran = pengeluaran,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pengeluaran) },
                onDeleteClick = {
                    onDeleteClick(pengeluaran)
                }
            )
        }
    }
}

@Composable
fun PengCard(
    pengeluaran: Pengeluaran,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pengeluaran) -> Unit = {}
) {
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pengeluaran.idpengeluaran,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onDeleteClick(pengeluaran) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = pengeluaran.idaset,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = pengeluaran.idkategori,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pengeluaran.tanggaltransaksi,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}