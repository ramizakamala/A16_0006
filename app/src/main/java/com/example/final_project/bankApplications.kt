package com.example.final_project

import android.app.Application
import com.example.final_project.di.AsetContainer
import com.example.final_project.di.KateContainer
import com.example.final_project.di.KategoriContainer
import com.example.final_project.di.PendContainer
import com.example.final_project.di.PendapatanContainer
import com.example.final_project.di.PengContainer
import com.example.final_project.di.PengeluaranContainer
import com.example.final_project.di.setContainer

class bankApplications : Application() {
    lateinit var containerP: PendapatanContainer
    lateinit var containerN: PengeluaranContainer
    lateinit var containerA: AsetContainer
    lateinit var containerK: KategoriContainer

    override fun onCreate() {
        super.onCreate()
        containerP = PendContainer()
        containerN = PengContainer()
        containerA = setContainer()
        containerK = KateContainer()
    }
}