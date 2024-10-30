package com.example.laboratorio7_retrofit.ui.market.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.laboratorio7_retrofit.database.data.SupermarketItemEntity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.laboratorio7_retrofit.navigation.AppBar
import com.example.laboratorio7_retrofit.ui.market.viewModel.SupermarketViewModel
import java.io.File
@Composable
fun SupermarketListScreen(
    viewModel: SupermarketViewModel,
    navController: NavController
) {
    val items by viewModel.items.observeAsState(initial = emptyList())

    Scaffold(
        topBar = { AppBar(title = "Lista de Supermercado", navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addItem")
                },
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Artículo"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(items) { item ->
                SupermarketItemCard(item = item, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SupermarketItemCard(
    item: SupermarketItemEntity,
    viewModel: SupermarketViewModel
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            item.imagePath?.let { path ->
                Image(
                    painter = rememberAsyncImagePainter(File(path)),
                    contentDescription = "Item Image",
                    modifier = Modifier.size(88.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = item.itemName, style = MaterialTheme.typography.titleMedium)
                Text(text = "Cantidad: ${item.quantity}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { viewModel.deleteItem(item) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Item"
                )
            }
        }
    }
}
