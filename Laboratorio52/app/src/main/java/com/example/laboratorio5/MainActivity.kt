package com.example.laboratorio5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio5.ui.theme.ConcertList
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import com.example.laboratorio5.ui.theme.ListadoActivity
import com.example.laboratorio5.ui.theme.PerfilActivity
import com.example.laboratorio5.ui.theme.Perfilfun
import kotlinx.coroutines.launch


val songs = listOf(
    Concert(
        Nombre = "Be Quiet and Drive",
        Artista = "Deftones",
        Descripcion = "Explorando los límites del rock alternativo con un viaje sonoro intenso.",
        Fecha = "FEB 28",
        Lugar = "US",
        imagen = R.drawable.deftones
    ),
    Concert(
        Nombre = "Loving Machine",
        Artista = "TV Girl",
        Descripcion = "Pop indie con letras nostálgicas y ritmos pegajosos.",
        Fecha = "MAY 03",
        Lugar = "Canada",
        imagen = R.drawable.tv
    ),
    Concert(
        Nombre = "A Pearl",
        Artista = "Mitski",
        Descripcion = "Mitski cautiva con su lirismo emocional y melodías profundas.",
        Fecha = "FEB 19",
        Lugar = "Germany",
        imagen = R.drawable.mistki
    ),
    Concert(
        Nombre = "Lovefool",
        Artista = "The Cardigans",
        Descripcion = "Un clásico de los 90, reviviendo el pop alternativo en todo su esplendor.",
        Fecha = "AUG 10",
        Lugar = "Japan",
        imagen = R.drawable.cardigans
    ),
    Concert(
        Nombre = "Cats on Mars",
        Artista = "SEATBELTS",
        Descripcion = "Jazz futurista con un toque de anime, directamente de Cowboy Bebop.",
        Fecha = "MAY 15",
        Lugar = "US",
        imagen = R.drawable.mars
    ),
    Concert(
        Nombre = "Little Dark Age",
        Artista = "MGMT",
        Descripcion = "Una mezcla de psicodelia y pop electrónico que te hará mover los pies.",
        Fecha = "JUL 22",
        Lugar = "Mexico",
        imagen = R.drawable.mgmt
    ),
    Concert(
        Nombre = "Motion Sickness",
        Artista = "Phoebe Bridgers",
        Descripcion = "Indie folk desgarrador, lleno de sentimientos y emociones profundas.",
        Fecha = "SEP 04",
        Lugar = "France",
        imagen = R.drawable.phoebe
    ),
    Concert(
        Nombre = "Space Oddity",
        Artista = "David Bowie",
        Descripcion = "El ícono del glam rock nos lleva en un viaje por las estrellas.",
        Fecha = "DEC 12",
        Lugar = "UK",
        imagen = R.drawable.bowie
    ),
    Concert(
        Nombre = "The Less I Know The Better",
        Artista = "Tame Impala",
        Descripcion = "Psychedelic pop que desafía los límites del sonido moderno.",
        Fecha = "NOV 18",
        Lugar = "Australia",
        imagen = R.drawable.tame_impala
    ),
    Concert(
        Nombre = "Take Me Out",
        Artista = "Franz Ferdinand",
        Descripcion = "Rock alternativo que te invita a moverte con su ritmo único.",
        Fecha = "OCT 30",
        Lugar = "Brazil",
        imagen = R.drawable.franz_ferdinand
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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


@Composable
fun NavigationDrawerScreen(
    context: Context,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 48.dp, end = 16.dp)
                ) {
                    Text(text = "Menu", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Navegar a la pantalla de perfil
                    TextButton(onClick = {
                        val intent = Intent(context, PerfilActivity::class.java)
                        context.startActivity(intent)
                        scope.launch { drawerState.close() }
                    }) {
                        Text(text = "Perfil")
                    }

                    // Navegar a la lista de conciertos
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = {
                        val intent = Intent(context, ListadoActivity::class.java)
                        context.startActivity(intent)
                        scope.launch { drawerState.close() }
                    }) {
                        Text(text = "Lista de Conciertos")
                    }

                    //Navegar en la principal de todos los conciertos
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = {
                        val intent = Intent(context, ConcertList::class.java)
                        context.startActivity(intent)
                        scope.launch { drawerState.close() }
                    }) {
                        Text(text = "Todos los Conciertos")
                    }
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 24.dp)
                    ) {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                },
                content = { innerPadding ->
                    Box(modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)) {
                        content(innerPadding)
                    }
                }
            )
        }
    )
}







@Preview(showBackground = true)
@Composable
fun DarkModePreview() {

    Laboratorio5Theme(darkTheme = true) {
        //MainScreen(concerts = songs)
        //Perfilfun()
        //ConcertList(concerts = songs)
        /*
        ConcertDetailScreen(
            name = "Be Quiet and Drive",
            location = "US",
            date = "FEB 28",
            description = "Around the Fur",
            image = R.drawable.deftones //ejemplo de una salida
        )
        */


    }
}

