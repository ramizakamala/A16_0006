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
import com.example.final_project.ui.viewmodel.Kategoriviewmodel.HomeKViewModel
import com.example.final_project.ui.viewmodel.Kategoriviewmodel.InsertKViewModel
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
        initializer { HomePViewModel(bankApplications().containerP.pendapatanRepository) }
        initializer { InsertPViewModel(bankApplications().containerP.pendapatanRepository) }
        initializer { UpdatePViewModel(bankApplications().containerP.pendapatanRepository) }
        initializer { DetailPViewModel(bankApplications().containerP.pendapatanRepository) }

        // pengeluaran
        initializer { HomeViewModel(bankApplications().containerN.pengeluaranRepository) }
        initializer { InsertViewModel(bankApplications().containerN.pengeluaranRepository) }
        initializer { UpdateViewModel(bankApplications().containerN.pengeluaranRepository) }
        initializer { DetailViewModel(bankApplications().containerN.pengeluaranRepository) }

        // aset
        initializer { HomeAViewModel(bankApplications().containerA.asetRepository) }
        initializer { InsertAViewModel(bankApplications().containerA.asetRepository) }
        initializer { UpdateAViewModel(bankApplications().containerA.asetRepository) }
        initializer { DetailAviewModel(bankApplications().containerA.asetRepository) }

        // kategori
        initializer { HomeKViewModel(bankApplications().containerK.kategoriRepository) }
        initializer { InsertKViewModel(bankApplications().containerK.kategoriRepository) }
        initializer { UpdateAViewModel(bankApplications().containerA.asetRepository) }
        initializer { DetailAviewModel(bankApplications().containerA.asetRepository) }
    }
}