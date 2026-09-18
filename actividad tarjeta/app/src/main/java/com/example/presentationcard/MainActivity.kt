package com.example.presentationcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentationcard.ui.theme.PresentationCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PresentationCardTheme {

                TarjetaPresentacion()
            }
        }
    }
}

@Composable
fun TarjetaPresentacion() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        SeccionEncabezado()
        SeccionContacto()
    }
}

@Composable
fun SeccionEncabezado() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "Fabian Balzeca Rezabala",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        Text(
            text = "Ingeniero de Software",
            fontSize = 16.sp,
            color = Color(0xFF3DDC84)
        )
    }
}

@Composable
fun FilaContacto(icono: androidx.compose.ui.graphics.vector.ImageVector, texto: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.7f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = Color(0xFF3DDC84),
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = texto,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SeccionContacto() {
    Column(
        modifier = Modifier.padding(bottom = 32.dp)
    ) {
        FilaContacto(icono = Icons.Filled.Phone, texto = "+593 99 999 9999")
        FilaContacto(icono = Icons.Filled.Email, texto = "correo@ejemplo.com")
        FilaContacto(icono = Icons.Filled.Share, texto = "@tu_usuario")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTarjetaPresentacion() {
    PresentationCardTheme {
        TarjetaPresentacion()
    }
}