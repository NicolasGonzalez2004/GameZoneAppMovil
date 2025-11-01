package com.example.gamezone.registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamezone.ui.theme.GameZoneTheme

private val GENEROS = listOf("FICCION", "NO FICCION", "MISTERIO", "TERROR", "SUSPENSO", "HISTORIA")

@Composable
fun RegisterScreen(
    onRegistered: () -> Unit
) {
    // Campos
    var nombre by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var pass2 by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf<String?>(null) }

    // Errores
    var eNombre by rememberSaveable { mutableStateOf<String?>(null) }
    var eEmail by rememberSaveable { mutableStateOf<String?>(null) }
    var ePass by rememberSaveable { mutableStateOf<String?>(null) }
    var ePass2 by rememberSaveable { mutableStateOf<String?>(null) }
    var eTelefono by rememberSaveable { mutableStateOf<String?>(null) }
    var eGenero by rememberSaveable { mutableStateOf<String?>(null) }

    fun validar(): Boolean {
        var ok = true

        // Nombre: no vacío, solo letras y espacios, máx 100
        eNombre = when {
            nombre.isBlank() -> { ok = false; "Ingresa tu nombre completo" }
            nombre.length > 100 -> { ok = false; "Máximo 100 caracteres" }
            !nombre.matches(Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) -> { ok = false; "Solo letras y espacios" }
            else -> null
        }

        // Email: formato y dominio @duoc.cl, máx 60
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@duoc\\.cl\$", RegexOption.IGNORE_CASE)
        eEmail = when {
            email.isBlank() -> { ok = false; "Ingresa tu correo @duoc.cl" }
            email.length > 60 -> { ok = false; "Máximo 60 caracteres" }
            !emailRegex.matches(email) -> { ok = false; "Debe ser un correo válido @duoc.cl" }
            else -> null // “Único” se valida contra BD en una app real
        }

        // Contraseña: ≥10, 1 mayúscula, 1 minúscula, 1 número, 1 especial
        val passRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%&!*?._-]).{10,}\$")
        ePass = when {
            !passRegex.matches(pass) -> {
                ok = false
                "Mín. 10 caracteres con mayúscula, minúscula, número y símbolo"
            }
            else -> null
        }

        // Confirmación
        ePass2 = when {
            pass2 != pass -> { ok = false; "Las contraseñas no coinciden" }
            else -> null
        }

        // Teléfono: opcional; si viene, solo dígitos 7–12
        eTelefono = when {
            telefono.isBlank() -> null
            !telefono.matches(Regex("^\\d{7,12}\$")) -> { ok = false; "Solo dígitos (7 a 12)" }
            else -> null
        }

        // Género: obligatorio (seleccionar uno)
        eGenero = if (genero == null) { ok = false; "Selecciona un género" } else null

        return ok
    }

    val scroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Crear cuenta GameZone", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre.take(100),
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                isError = eNombre != null,
                supportingText = { if (eNombre != null) Text(eNombre!!) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = email.take(60),
                onValueChange = { email = it },
                label = { Text("Correo @duoc.cl") },
                isError = eEmail != null,
                supportingText = { if (eEmail != null) Text(eEmail!!) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") },
                isError = ePass != null,
                supportingText = { if (ePass != null) Text(ePass!!) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = pass2,
                onValueChange = { pass2 = it },
                label = { Text("Confirmar contraseña") },
                isError = ePass2 != null,
                supportingText = { if (ePass2 != null) Text(ePass2!!) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it.filter(Char::isDigit) },
                label = { Text("Teléfono (opcional)") },
                isError = eTelefono != null,
                supportingText = { if (eTelefono != null) Text(eTelefono!!) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Género favorito",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(8.dp))

                    GENEROS.forEach { g ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            RadioButton(
                                selected = genero == g,
                                onClick = { genero = g }
                            )
                            Text(g, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                    if (eGenero != null) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            eGenero!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { if (validar()) onRegistered() },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Registrarme") }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewRegisterScreen() {
    GameZoneTheme { RegisterScreen(onRegistered = {}) }
}


