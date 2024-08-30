package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Divider
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.laboratorio5.R
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import com.example.laboratorio5.ui.theme.Purple40
import com.example.laboratorio5.ui.theme.Purple80


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


class ConcertList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(concerts = songs)
                }
            }
        }
    }
}

//Se crea una columna para todos los conciertos
@Composable
fun MainScreen(concerts: List<Concert>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        //El titul de arriba
        Text(
            text = "Lista de Conciertos",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Purple40)
                .padding(25.dp)
                .size(35.dp),

            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Se llama funcion de SongList para organizar los conciertos
        SongList(concerts = concerts)
    }
}

//Por cada concierto se le agrega su imagen, su nomrbe y su artista
@Composable
fun ConcertItem(concert: Concert) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = concert.imagen),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = concert.Nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = concert.Artista, fontSize = 14.sp, color = Color.Gray)
    }
}

//Se envia como parametro la lista y por cada uno los va colocando en la funcion de ConcertItem
@Composable
fun SongList(concerts: List<Concert>) {
    LazyVerticalGrid(
        //Para que sean dos filas
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(concerts) { concert ->
            ConcertItem(concert = concert)
        }
    }
}

