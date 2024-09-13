package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import com.example.laboratorio5.ui.theme.Perfilfun

class ConcertDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener los datos del intent
        val concertName = intent.getStringExtra("concert_name") ?: "Nombre no disponible"
        val concertLocation = intent.getStringExtra("concert_location") ?: "Lugar no disponible"
        val concertDate = intent.getStringExtra("concert_date") ?: "Fecha no disponible"
        val concertDescription = intent.getStringExtra("concert_description") ?: "Descripción no disponible"
        val concertImage = intent.getIntExtra("concert_image", R.drawable.ic_launcher_foreground)

        setContent {
            Laboratorio5Theme {
                NavigationDrawerScreen(context = this) {
                    ConcertDetailScreen(
                        name = concertName,
                        location = concertLocation,
                        date = concertDate,
                        description = concertDescription,
                        image = concertImage
                    )

                }
            }
        }
    }
}

@Composable
fun ConcertDetailScreen(name: String, location: String, date: String, description: String, image: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen hasta arriba
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del Concierto
            Text(
                text = name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Lugar
            Text(
                text = "Lugar: $location",
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            // Fecha
            Text(
                text = "Fecha: $date",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Descripcion
            Text(
                text = "Descripcion: \n$description",
                fontSize = 17.sp
            )

            // Espaciador grande para empujar contenido hacia arriba
            Spacer(modifier = Modifier.weight(1f))
        }

        // Fila de botones en la parte inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.45f) // Botones más grandes ocupando el 45% del ancho cada uno
                    .height(60.dp)
            ) {
                Text(text = "Favorites")
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(0.75f) // Botones más grandes ocupando el 45% del ancho cada uno
                    .height(60.dp)
            ) {
                Text(text = "Buy")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConcertDetailScreen() {
    Laboratorio5Theme {
        ConcertDetailScreen(
            name = "Be Quiet and Drive",
            location = "US",
            date = "FEB 28",
            description = "Around the Fur",
            image = R.drawable.deftones // ejemplo de una salida
        )
    }
}
