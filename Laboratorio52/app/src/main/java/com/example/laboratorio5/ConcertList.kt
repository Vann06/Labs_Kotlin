package com.example.laboratorio5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.laboratorio5.ui.theme.Laboratorio5Theme

class ConcertList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                NavigationDrawerScreen(context = this) {
                    MainScreen(concerts = songs) { concert ->
                        val intent = Intent(this, ConcertDetailActivity::class.java).apply {
                            putExtra("concert_name", concert.Nombre)
                            putExtra("concert_location", concert.Lugar)
                            putExtra("concert_date", concert.Fecha)
                            putExtra("concert_description", concert.Descripcion)
                            putExtra("concert_image", concert.imagen)
                        }
                        startActivity(intent)
                    }
                }

            }
        }
    }
}

@Composable
fun MainScreen(concerts: List<Concert>, onItemClick: (Concert) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)  // Fondo blanco
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de Conciertos",
            fontSize = 32.sp,  // Tamaño grande del título
            fontWeight = FontWeight.Bold,  // Texto en negrita
            color = Color.Black,  // Color del texto negro
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)  // Centrado horizontalmente
        )

        Spacer(modifier = Modifier.height(16.dp))

        SongList(concerts = concerts,
               onItemClick = onItemClick)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongList(concerts: List<Concert>, onItemClick: (Concert) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // 2 columnas
        contentPadding = PaddingValues(8.dp)
    ) {
        items(concerts) { concert ->
            ConcertItem(concert = concert,
                        onItemClick = onItemClick)
        }
    }
}

@Composable
fun ConcertItem(concert: Concert, onItemClick: (Concert) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)  // Fondo blanco
            .wrapContentSize()
            .clickable {onItemClick(concert) },//Funcionalidad para hacerle click
        horizontalAlignment = Alignment.CenterHorizontally  // Centrado
    ) {
        Image(
            painter = painterResource(id = concert.imagen),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = concert.Nombre,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black  // Texto en negro
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = concert.Artista,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val sampleConcerts = listOf(
        Concert(
            Nombre = "MISTKI TE AMO",
            Artista = "Mitski",
            Descripcion = "Mitski la mas chula",
            Fecha = "28 FEB",
            Lugar = "Guatemala",
            imagen = R.drawable.mistki
        ),
        Concert(
            Nombre = "Concert 2",
            Artista = "Cardigans",
            Descripcion = "Mitski la mas chula",
            Fecha = "28 FEB",
            Lugar = "Guatemala",
            imagen = R.drawable.cardigans
        )
        // Agrega más conciertos de ejemplo si lo deseas
    )

    Laboratorio5Theme {
        MainScreen(concerts = sampleConcerts,
            onItemClick = { concert ->
                // Acción vacía para vista previa
            })
    }
}
