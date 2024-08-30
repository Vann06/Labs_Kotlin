package com.example.laboratorio5.ui.theme


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Divider
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.text.font.FontWeight
import com.example.laboratorio5.R
import com.example.laboratorio5.ui.theme.Laboratorio5Theme


class Perfil: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            Laboratorio5Theme{
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ){
                    Perfilfun()
                }
            }
        }
    }
}

//Superficie principal del perfil
@Composable
fun Perfilfun(modifier: Modifier = Modifier) {
    //Color gris en el fondo
    Surface(color = Color.LightGray) {
        //Imagen de arriba de fondo
        Image(painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Perfil",
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        )
        //Se crea una columna separada para las opciones
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // Icono de arriba
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Imagen del perfil
                Image(
                    painter = painterResource(id = R.drawable.ic_perfil),
                    contentDescription = "Perfil",
                    modifier = modifier.size(195.dp)
                )
                //Nombre del usuario
                Text(
                    text = "Vianka Castro",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                Text(text = "")

                Divider(color = Color.Black, modifier = Modifier.padding(vertical = 16.dp))
            }
            //Luego va colocando cada opcion desde la funcion de PerfilItems
            PerfilItems(
                iconResId = R.drawable.ic_perfil, // Reemplaza con tu drawable
                text = "     Edit Profile"
            )
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 16.dp))
            PerfilItems(
                iconResId = R.drawable.lock, // Reemplaza con tu drawable
                text = "     Reset Password"
            )
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 16.dp))
            PerfilItems(
                iconResId = R.drawable.notification, // Reemplaza con tu drawable
                text = "     Notifications"
            )
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 16.dp))
            PerfilItems(
                iconResId = R.drawable.favorite, // Reemplaza con tu drawable
                text = "     Favorites"
            )
        }
    }
}

//Crea la forma general de las opciones con la imagen y el texto que se quiere elegir
@Composable
fun PerfilItems(iconResId: Int, text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        // Imagen del ícono (a la izquierda)
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Icon",
            modifier = modifier
                .size(45.dp)
        )

        // Texto (en el centro)
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)  // Esto empuja el texto hacia la izquierda
                .padding(start = 16.dp)  // Añade un padding desde la izquierda
        )

        // Imagen "bleach" alineada a la derecha
        Image(
            painter = painterResource(id = R.drawable.bleach),
            contentDescription = "Arrow",
            modifier = modifier
                .size(30.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Laboratorio5Theme {
        Perfilfun()
    }
}
