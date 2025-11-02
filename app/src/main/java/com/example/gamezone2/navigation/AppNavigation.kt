package com.example.gamezone2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamezone2.inicio.InicioScreen
import com.example.gamezone2.login.LoginScreen
import com.example.gamezone2.registro.RegisterScreen

object Routes {
    const val LOGIN = "login"
    const val INICIO = "inicio"
    const val REGISTRO = "registro"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LOGIN) {


        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.INICIO) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onCreateAccount = {
                    navController.navigate(Routes.REGISTRO)
                }
            )
        }

        // 🔹 Pantalla INICIO
        composable(Routes.INICIO) {
            InicioScreen(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.INICIO) { inclusive = true }
                    }
                }
            )
        }

        // 🔹 Pantalla REGISTRO
        composable(Routes.REGISTRO) {
            RegisterScreen(
                onRegistered = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTRO) { inclusive = true }
                    }
                }
            )
        }
    }
}


