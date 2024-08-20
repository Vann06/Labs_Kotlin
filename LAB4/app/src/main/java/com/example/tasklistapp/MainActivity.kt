package com.example.tasklistapp

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tasklistapp.ui.theme.TaskListAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListAppTheme {
                TaskListScreen()
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    val context = LocalContext.current

    // Estado para la lista de tareas (nombres de comida con URLs de imágenes)
    val tasks = remember { mutableStateListOf<Task>() }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    // Solicitar permisos en el inicio
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    TaskListContent(
        tasks = tasks,
        onAddTask = { title, imageUri ->
            tasks.add(Task(title, imageUri))
        }
    )
}

@Composable
fun TaskListContent(
    tasks: MutableList<Task>,
    onAddTask: (String, String?) -> Unit
) {
    var newTaskTitle by remember { mutableStateOf("") }
    var newImageUrl by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Lista de Comidas",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
            )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para el nombre de la comida
        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it
                            errorMessage = ""
                            },
            label = { Text("Nombre de la comida",

                ) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para la URL de la imagen
        OutlinedTextField(
            value = newImageUrl,
            onValueChange = { newImageUrl = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if(errorMessage.isNotEmpty()){
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }
        // Botón para agregar la comida a la lista
        Button(
            onClick = {
                if (newTaskTitle.isNotEmpty() && newImageUrl.isNotEmpty()) {
                    onAddTask(newTaskTitle, newImageUrl)
                    newTaskTitle = ""
                    newImageUrl = ""
                }
                else{
                    errorMessage = "ERROR: Ingrese todos los datos correspondientes."
                }
            }
        ) {

            Text("Agregar")

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de tareas utilizando LazyColumn
        LazyColumn {
            items(tasks.size) { index ->
                TaskItem(
                    task = tasks[index],
                    onTaskClick = { tasks.removeAt(index)}
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskClick: () -> Task) {
    var grayscale by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTaskClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = task.title, style = MaterialTheme.typography.bodyLarge)

        task.imageUri?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                colorFilter = if (grayscale) androidx.compose.ui.graphics.ColorFilter.colorMatrix(
                    androidx.compose.ui.graphics.ColorMatrix().apply { setToSaturation(0f) }) else null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModePreview() {
    TaskListAppTheme(darkTheme = true) {
        TaskListScreen()
    }
}


data class Task(val title: String, val imageUri: String?)