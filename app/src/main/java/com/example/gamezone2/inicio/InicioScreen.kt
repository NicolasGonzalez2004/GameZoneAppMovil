package com.example.gamezone.inicio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.tooling.preview.Preview
import com.example.gamezone.ui.theme.GameZoneTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewInicioScreen() {
    GameZoneTheme {
        InicioScreen(onLogout = {})
    }
}

@Composable
fun InicioScreen(
    onLogout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a GameZone ",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Aquí podrías mostrar tus juegos favoritos o novedades."
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = onLogout) {
                Text("Iniciar Sesión")
            }
        }
    }
}

