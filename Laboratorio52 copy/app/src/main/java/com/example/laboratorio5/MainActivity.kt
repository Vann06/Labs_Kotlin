package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio5.ui.theme.ConcertList
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import com.example.laboratorio5.ui.theme.Perfilfun


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MainScreen(concerts = songs)
                    Perfilfun()
                    //ConcertList(concerts = com.example.laboratorio5.ui.theme.songs)

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DarkModePreview() {
    Laboratorio5Theme(darkTheme = true) {
        MainScreen(concerts = songs)
        //Perfilfun()

    }
}

