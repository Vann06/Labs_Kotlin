package com.example.laboratorio7_retrofit.ui.market.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.laboratorio7_retrofit.database.data.SupermarketItemEntity
import com.example.laboratorio7_retrofit.navigation.AppBar
import com.example.laboratorio7_retrofit.ui.market.viewModel.SupermarketViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import java.util.jar.Manifest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    viewModel: SupermarketViewModel,
    navController: NavController,
    item: SupermarketItemEntity? = null
) {
    var itemName by remember { mutableStateOf(item?.itemName ?: "") }
    var quantity by remember { mutableStateOf(item?.quantity?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(item?.imagePath) }
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Variable para almacenar la URI de la imagen capturada
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Lanzador para capturar imagen
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            // Si la captura de la imagen fue exitosa, actualizamos `imagePath` con la URI de la imagen
            imagePath = capturedImageUri?.toString()
        }
    }

    // Lanzador para solicitar permiso de cámara
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Crear archivo y lanzar la cámara si el permiso está concedido
            val imageFile = createImageFile(context)
            capturedImageUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                imageFile
            )

            capturedImageUri?.let { uri ->
                cameraLauncher.launch(uri)
            }
        } else {
            // Muestra un mensaje si el permiso es denegado
            Toast.makeText(context, "Camera permission is required to take pictures", Toast.LENGTH_SHORT).show()
        }
    }

    // Solicita el permiso cuando se haga clic en el botón de captura de imagen
    Scaffold(
        topBar = { AppBar(title = "Agregar Artículo", navController = navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (itemName.isNotBlank() && quantity.isNotBlank()) {
                    val qty = quantity.toIntOrNull() ?: 0
                    if (item == null) {
                        viewModel.addItem(itemName, qty, imagePath)
                    } else {
                        viewModel.updateItem(item.id, itemName, qty, imagePath)
                    }
                    navController.popBackStack()
                } else {
                    showError = true
                }
            }) {
                Icon(Icons.Default.Check, contentDescription = "Guardar Artículo")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text("Nombre del Artículo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar la imagen capturada o seleccionada
            if (imagePath != null) {
                Image(
                    painter = rememberAsyncImagePainter(imagePath),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }) {
                Text("Capturar Imagen")
            }

            if (showError) {
                Text(
                    text = "Por favor completa todos los campos.",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

// Función para crear un archivo de imagen en un directorio específico
fun createImageFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
}

