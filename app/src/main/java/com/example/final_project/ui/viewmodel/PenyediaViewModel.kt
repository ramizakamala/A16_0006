package com.example.final_project.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.final_project.bankApplications
import com.example.final_project.ui.viewmodel.Asetviewmodel.DetailAviewModel
import com.example.final_project.ui.viewmodel.Asetviewmodel.HomeAViewModel
import com.example.final_project.ui.viewmodel.Asetviewmodel.InsertAViewModel
import com.example.final_project.ui.viewmodel.Asetviewmodel.UpdateAViewModel
import com.example.final_project.ui.viewmodel.Kategoriviewmodel.DetailKViewModel
import com.example.final_project.ui.viewmodel.Kategoriviewmodel.HomeKViewModel
import com.example.final_project.ui.viewmodel.Kategoriviewmodel.InsertKViewModel
import com.example.final_project.ui.viewmodel.Kategoriviewmodel.UpdateKViewModel
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.DetailPViewModel
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.HomePViewModel
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.InsertPViewModel
import com.example.final_project.ui.viewmodel.Pendapatanviewmodel.UpdatePViewModel
import com.example.final_project.ui.viewmodel.Pengeluaranviewmodel.DetailViewModel
import com.example.final_project.ui.viewmodel.Pengeluaranviewmodel.HomeViewModel
import com.example.final_project.ui.viewmodel.Pengeluaranviewmodel.InsertViewModel
import com.example.final_project.ui.viewmodel.Pengeluaranviewmodel.UpdateViewModel

fun CreationExtras.aplikasibank(): bankApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as bankApplications)

object PenyediaViewModel{
    val Factory = viewModelFactory {
        // pendapatan
        initializer { HomePViewModel(aplikasibank().containerP.pendapatanRepository) }
        initializer { InsertPViewModel(aplikasibank().containerP.pendapatanRepository) }
        initializer { UpdatePViewModel(aplikasibank().containerP.pendapatanRepository) }
        initializer { DetailPViewModel(aplikasibank().containerP.pendapatanRepository) }

        // pengeluaran
        initializer { HomeViewModel(aplikasibank().containerN.pengeluaranRepository) }
        initializer { InsertViewModel(aplikasibank().containerN.pengeluaranRepository) }
        initializer { UpdateViewModel(aplikasibank().containerN.pengeluaranRepository) }
        initializer { DetailViewModel(aplikasibank().containerN.pengeluaranRepository) }

        // aset
        initializer { HomeAViewModel(aplikasibank().containerA.asetRepository) }
        initializer { InsertAViewModel(aplikasibank().containerA.asetRepository) }
        initializer { UpdateAViewModel(aplikasibank().containerA.asetRepository) }
        initializer { DetailAviewModel(aplikasibank().containerA.asetRepository) }

        // kategori
        initializer { HomeKViewModel(aplikasibank().containerK.kategoriRepository) }
        initializer { InsertKViewModel(aplikasibank().containerK.kategoriRepository) }
        initializer { UpdateKViewModel(aplikasibank().containerK.kategoriRepository) }
        initializer { DetailKViewModel(aplikasibank().containerK.kategoriRepository) }
    }
}