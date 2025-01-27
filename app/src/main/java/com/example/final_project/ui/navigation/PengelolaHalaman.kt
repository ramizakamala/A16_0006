package com.example.final_project.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.final_project.ui.view.Asetview.DestinasiDetailA
import com.example.final_project.ui.view.Asetview.DestinasiEntryA
import com.example.final_project.ui.view.Asetview.DestinasiHomeA
import com.example.final_project.ui.view.Asetview.DestinasiUpdateA
import com.example.final_project.ui.view.Asetview.DetailAview
import com.example.final_project.ui.view.Asetview.EntrySetScreen
import com.example.final_project.ui.view.Asetview.HomeAScreen
import com.example.final_project.ui.view.Asetview.UpdateSetScreen
import com.example.final_project.ui.view.HomePage
import com.example.final_project.ui.view.Kategoriview.DestinasiDetailK
import com.example.final_project.ui.view.Kategoriview.DestinasiEntryK
import com.example.final_project.ui.view.Kategoriview.DestinasiHomeK
import com.example.final_project.ui.view.Kategoriview.DestinasiUpdateK
import com.example.final_project.ui.view.Kategoriview.DetailKView
import com.example.final_project.ui.view.Kategoriview.EntrykatScreen
import com.example.final_project.ui.view.Kategoriview.HomeKScreen
import com.example.final_project.ui.view.Kategoriview.UpdateKatScreen
import com.example.final_project.ui.view.Pendapatanview.DestinasiDetailP
import com.example.final_project.ui.view.Pendapatanview.DestinasiEntryP
import com.example.final_project.ui.view.Pendapatanview.DestinasiHomeP
import com.example.final_project.ui.view.Pendapatanview.DestinasiUpdateP
import com.example.final_project.ui.view.Pendapatanview.DetailPView
import com.example.final_project.ui.view.Pendapatanview.EntryPendScreen
import com.example.final_project.ui.view.Pendapatanview.HomeScreen
import com.example.final_project.ui.view.Pendapatanview.UpdatePendScreen
import com.example.final_project.ui.view.Pengeluaranview.DestinasiDetailN
import com.example.final_project.ui.view.Pengeluaranview.DestinasiEntryN
import com.example.final_project.ui.view.Pengeluaranview.DestinasiHomeN
import com.example.final_project.ui.view.Pengeluaranview.DestinasiUpdateN
import com.example.final_project.ui.view.Pengeluaranview.DetailNView
import com.example.final_project.ui.view.Pengeluaranview.EntryPengScreen
import com.example.final_project.ui.view.Pengeluaranview.HomeNScreen
import com.example.final_project.ui.view.Pengeluaranview.UpdatePengScreen
import com.example.final_project.ui.view.Splashview

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = HomePage.route,
        modifier = Modifier,
    ) {
        composable(HomePage.route) {
            Splashview(
                onPendapatanButton = {
                    navController.navigate(DestinasiHomeP.route)
                },
                onPengeluaranButton = {
                    navController.navigate(DestinasiHomeN.route)
                },
                onAsetButton = {
                    navController.navigate(DestinasiHomeA.route)
                },
                onKategoriButton = {
                    navController.navigate(DestinasiHomeK.route)
                }
            )
        }

        //Pendapatan
        composable(DestinasiHomeP.route) {
            HomeScreen(
                navigateToitemEntry = { navController.navigate(DestinasiEntryP.route) },
                navigateBack = {
                    navController.navigate(HomePage.route) {
                        popUpTo(HomePage.route) {
                            inclusive = true
                        }
                    }
                },
                onDetailClick = { idpendapatan ->
                    navController.navigate("${DestinasiDetailK.route}/$idpendapatan")
                    println(
                        "PengelolaHalaman: idpendapatan = $idpendapatan"
                    )
                },
                onUpdateClick = { idpendapatan ->
                    navController.navigate("${DestinasiUpdateP.route}/$idpendapatan")
                    println(
                        "PengelolaHalaman: idpendapatan = $idpendapatan"
                    )
                }
            )
        }
        composable(DestinasiEntryP.route) {
            EntryPendScreen(navigateBack = {
                navController.navigate(DestinasiHomeP.route) {
                    popUpTo(DestinasiHomeP.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailP.routeWithArgs, arguments = listOf(
            navArgument(DestinasiDetailP.idpendapatan) {
                type = NavType.StringType
            }
        )
        ) {
            val idpendapatan = it.arguments?.getString(DestinasiDetailP.idpendapatan)
            idpendapatan?.let { idpendapatan ->
                DetailPView(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateP.route}/$it")
                    },
                    idpendapatan = idpendapatan,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateP.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateP.idpendapatan) {
                    type = NavType.StringType
                }
            )
        ) {
            val idpendapatan = it.arguments?.getString(DestinasiUpdateP.idpendapatan)
            idpendapatan?.let { idpendapatan ->
                UpdatePendScreen(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    pendapatan = idpendapatan
                )
            }
        }

        //Pengeluaran
        composable(DestinasiHomeN.route) {
            HomeNScreen(
                navigateToitemEntry = { navController.navigate(DestinasiEntryN.route) },
                navigateBack = {
                    navController.navigate(HomePage.route) {
                        popUpTo(HomePage.route) {
                            inclusive = true
                        }
                    }
                },
                onDetailClick = { idpengeluaran ->
                    navController.navigate("${DestinasiDetailN.route}/$idpengeluaran")
                    println(
                        "PengelolaHalaman: idpengeluaran = $idpengeluaran"
                    )
                },
                onUpdateClick = { idpengeluaran ->
                    navController.navigate("${DestinasiUpdateN.route}/$idpengeluaran")
                    println(
                        "PengelolaHalaman: idpengeluaran = $idpengeluaran"
                    )
                }
            )
        }
        composable(DestinasiEntryN.route) {
            EntryPengScreen(navigateBack = {
                navController.navigate(DestinasiHomeN.route) {
                    popUpTo(DestinasiHomeN.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailN.routeWithArgs, arguments = listOf(
            navArgument(DestinasiDetailN.idpengeluaran) {
                type = NavType.StringType
            }
        )
        ) {
            val idpengeluaran = it.arguments?.getString(DestinasiDetailN.idpengeluaran)
            idpengeluaran?.let { idpengeluaran ->
                DetailNView(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateN.route}/$it")
                    },
                    idpengeluaran = idpengeluaran,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateN.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateN.idpengeluaran) {
                    type = NavType.StringType
                }
            )
        ) {
            val idpengeluaran = it.arguments?.getString(DestinasiUpdateN.idpengeluaran)
            idpengeluaran?.let { idpengeluaran ->
                UpdatePengScreen(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    pengeluaran = idpengeluaran
                )
            }
        }

        //Aset
        composable(DestinasiHomeA.route) {
            HomeAScreen(
                navigateToitemEntry = { navController.navigate(DestinasiEntryA.route) },
                onDetailClick = { idaset ->
                    navController.navigate("${DestinasiDetailK.route}/$idaset")
                    println(
                        "PengelolaHalaman: idaset = $idaset"
                    )
                },
                navigateBack = {
                    navController.navigate(HomePage.route) {
                        popUpTo(HomePage.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiEntryA.route) {
            EntrySetScreen(navigateBack = {
                navController.navigate(DestinasiHomeK.route) {
                    popUpTo(DestinasiHomeK.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailA.routeWithArgs, arguments = listOf(
            navArgument(DestinasiDetailA.idaset) {
                type = NavType.StringType
            }
        )
        ) {
            val idaset = it.arguments?.getString(DestinasiDetailA.idaset)
            idaset?.let { idaset ->
                DetailAview(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateA.route}/$it")
                    },
                    idaset = idaset,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateA.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateA.aset) {
                    type = NavType.StringType
                }
            )
        ) {
            val idaset = it.arguments?.getString(DestinasiUpdateA.aset)
            idaset?.let { idaset ->
                UpdateSetScreen(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    aset = idaset
                )
            }
        }

        //Kategori
        composable(DestinasiHomeK.route) {
            HomeKScreen(
                navigateToitemEntry = { navController.navigate(DestinasiEntryK.route) },
                onDetailClick = { idkategori ->
                    navController.navigate("${DestinasiDetailK.route}/$idkategori")
                    println(
                        "PengelolaHalaman: idkategori = $idkategori"
                    )
                },
                navigateBack = {
                    navController.navigate(HomePage.route) {
                        popUpTo(HomePage.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiEntryP.route) {
            EntrykatScreen(navigateBack = {
                navController.navigate(DestinasiHomeK.route) {
                    popUpTo(DestinasiHomeK.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailK.routeWithArgs, arguments = listOf(
            navArgument(DestinasiDetailK.idkategori) {
                type = NavType.StringType
            }
        )
        ) {
            val idkategori = it.arguments?.getString(DestinasiDetailK.idkategori)
            idkategori?.let { idkategori ->
                DetailKView(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateK.route}/$it")
                    },
                    idkategori = idkategori,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateK.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateK.kategori) {
                    type = NavType.StringType
                }
            )
        ) {
            val idkategori = it.arguments?.getString(DestinasiUpdateK.kategori)
            idkategori?.let { idkategori ->
                UpdateKatScreen(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    kategori = idkategori
                )
            }
        }
    }
}

