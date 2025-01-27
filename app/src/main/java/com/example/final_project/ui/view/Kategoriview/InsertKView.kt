    package com.example.final_project.ui.view.Kategoriview

    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material3.Button
    import androidx.compose.material3.Divider
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.input.nestedscroll.nestedScroll
    import androidx.compose.ui.unit.dp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import com.example.final_project.ui.customwidget.CostumeTopAppBar
    import com.example.final_project.ui.navigation.DestinasiNavigasi
    import com.example.final_project.ui.viewmodel.Kategoriviewmodel.InsertKViewModel
    import com.example.final_project.ui.viewmodel.Kategoriviewmodel.InsertUiEvent
    import com.example.final_project.ui.viewmodel.Kategoriviewmodel.InsertUiState
    import com.example.final_project.ui.viewmodel.PenyediaViewModel
    import kotlinx.coroutines.launch

    object DestinasiEntryK: DestinasiNavigasi {
        override val route = "item_entry"
        override val titleRes = "Insert Kategori"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EntrykatScreen(
        navigateBack: () -> Unit,
        modifier: Modifier = Modifier,
        viewModel: InsertKViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ){
        val coroutineScope = rememberCoroutineScope()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CostumeTopAppBar(
                    title = DestinasiEntryK.titleRes,
                    canNavigateBack = true,
                    scrollBehavior = scrollBehavior,
                    navigateUp = navigateBack
                )
            }
        ){
                innerPadding ->
            EntryBody(
                insertUiState = viewModel.uiState,
                onKategoriValueChange = viewModel::updateInsertKatState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.insertKat()
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
    fun EntryBody(
        insertUiState: InsertUiState,
        onKategoriValueChange: (InsertUiEvent) -> Unit,
        onSaveClick: () -> Unit,
        modifier: Modifier = Modifier
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = modifier.padding(12.dp)
        ){
            FormInput(
                insertUiEvent = insertUiState.InsertUiEvent,
                onValueChange = onKategoriValueChange,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onSaveClick,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Simpan")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FormInput(
        insertUiEvent: InsertUiEvent,
        modifier: Modifier = Modifier,
        onValueChange: (InsertUiEvent) -> Unit = {},
        enabled: Boolean = true

    ){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            OutlinedTextField(
                value = insertUiEvent.idkategori,
                onValueChange = {onValueChange(insertUiEvent.copy(idkategori = it))},
                label = { Text(text = "Kategori")},
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = insertUiEvent.namakategori,
                onValueChange = {onValueChange(insertUiEvent.copy(namakategori = it))},
                label = { Text(text = "Nama Kategori")},
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            if(enabled){
                Text(
                    text = "Isi Semua Data",
                    modifier = Modifier.padding(12.dp)
                )
            }
            Divider(
                thickness = 8.dp,
                modifier = Modifier.padding(12.dp)
            )
        }
    }