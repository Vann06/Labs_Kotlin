package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio5.ui.theme.Laboratorio5Theme

class ConcertDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Base para evitar errores
        val concertName = intent.getStringExtra("concert_name")
        val concertLocation = intent.getStringExtra("concert_location")
        val concertDate = intent.getStringExtra("concert_date")
        val concertDescription = intent.getStringExtra("concert_description")
        val concertImage = intent.getIntExtra("concert_image", R.drawable.ic_launcher_foreground) // Default

        setContent {
            Laboratorio5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConcertDetailScreen(
                        name = concertName ?: "Unknown Concert",
                        location = concertLocation ?: "Unknown Location",
                        date = concertDate ?: "Unknown Date",
                        description = concertDescription ?: "No Description",
                        image = concertImage
                    )
                }
            }
        }
    }
}

//Columna para colocar todos los datos de un concierto en especifico
@Composable
fun ConcertDetailScreen(name: String, location: String, date: String, description: String, image: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
        // Text for concert description
        Text(
            text = "Descripcion: \n$description",
            fontSize = 17.sp
        )
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
            image = R.drawable.deftones //ejemplo de una salida
        )
    }
}
