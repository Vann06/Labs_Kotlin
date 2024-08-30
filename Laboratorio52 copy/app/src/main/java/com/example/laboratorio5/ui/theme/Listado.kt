package com.example.laboratorio5.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.laboratorio5.R
import com.example.laboratorio5.ui.theme.Laboratorio5Theme

class Concert(
    val Nombre: String ,
    val Artista: String,
    val fecha: String,
    val lugar: String,
    val Descripcion:String,
    val imagen : Int )

val songs = listOf(
    Concert("Be Quiet and Drive", "Deftones","FEB28","US","Around the Fur", R.drawable.deftones),
    Concert("Loving Machine", "TV Girl", "MAY03","Canada","Around the Fur",R.drawable.tv),
    Concert("A Pearl", "Mistki","FEB19","Germany", "Around the Fur",R.drawable.mistki),
    Concert("Lovefool","The Cardigans","AUG10","Japan","Around the Fur",R.drawable.cardigans),
    Concert("Cats on Mars","SEATBELTS","MAY15","US","Cowboy Bebop",R.drawable.mars)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ConcertList(concerts = songs)
                }
            }
        }
    }
}

//Crea la columna con todos los conciertos de la lista
@Composable
fun ConcertList(concerts: List<Concert>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Para cada uno en la lista de concerts va agregando en la funcion ConcertItem
        concerts.forEach { concert ->
            ConcertItem(concert = concert)
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 5.dp))
        }

    }
}

//SE crea este item para cada uno con su imagen, nombre y descripcion
@Composable
fun ConcertItem(concert: Concert) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
            Text(text = concert.lugar,
                style = MaterialTheme.typography.bodyMedium)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewConcertList() {
    Laboratorio5Theme {
        ConcertList(concerts = songs)
    }
}
