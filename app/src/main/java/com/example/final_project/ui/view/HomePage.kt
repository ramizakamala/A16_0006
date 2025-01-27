package com.example.final_project.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.final_project.R
import com.example.final_project.ui.navigation.DestinasiNavigasi

object HomePage : DestinasiNavigasi {
    override val route = "home page"
    override val titleRes = "Home page"
}
@Composable
fun Splashview(
    onPendapatanButton: () -> Unit,
    onPengeluaranButton: () -> Unit,
    onAsetButton: () -> Unit,
    onKategoriButton: () -> Unit,

    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.white
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63)
            ),
            onClick = {
                onPendapatanButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Pendapatan",
            )
        }
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF75afc4)
            ),
            onClick = {
                onPengeluaranButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Pengeluaran",
            )
        }
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7fabbb)
            ),
            onClick = {
                onAsetButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Aset",
            )
        }
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF87a7b3)
            ),
            onClick = {
                onKategoriButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Kategori",
            )
        }
    }
}
