package com.example.laboratorio5.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio5.Concert
import com.example.laboratorio5.ConcertDetailActivity
import com.example.laboratorio5.NavigationDrawerScreen
import com.example.laboratorio5.R
import com.example.laboratorio5.songs


class ListadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                NavigationDrawerScreen(context = this) {
                    ConcertList(concerts = songs){ concert ->
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


//Crea la columna con todos los conciertos de la lista
@Composable
fun ConcertList(concerts: List<Concert>, onItemClick: (Concert) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Para cada uno en la lista de concerts va agregando en la funcion ConcertItem
        concerts.forEach { concert ->
            ConcertItem(concert = concert,
                        onItemClick = onItemClick)
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 5.dp))
        }

    }
}

//SE crea este item para cada uno con su imagen, nombre y descripcion
@Composable
fun ConcertItem(concert: Concert, onItemClick: (Concert) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {onItemClick(concert) },//Funcionalidad para hacerle click
    ) {
        // Imagen circular pequeña
        Image(
            painter = painterResource(id = concert.imagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nombre y lugar del concierto
        Column(
            //modifier = Modifier.weight(1)
        ) {
            Text(text = concert.Nombre,
                style = MaterialTheme.typography.titleMedium)
            Text(text = concert.Lugar,
                style = MaterialTheme.typography.bodyMedium)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Laboratorio5Theme {
        //ConcertList(concerts = songs)
    }
}