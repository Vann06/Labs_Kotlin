package com.example.laboratorio7_retrofit.ui.categories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.laboratorio7_retrofit.R
import com.example.laboratorio7_retrofit.navigation.NavigationState
import com.example.laboratorio7_retrofit.navigation.navigateTo
import com.example.laboratorio7_retrofit.networking.response.categories.Categories

@Composable
fun MealCategory(meal: Categories, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp).clickable {
                        meal.name?.let {
                            navigateTo(
                                navController,
                                NavigationState.MealsRecipesList.createRoute(it)
                            )
                        }
                    }
            ) {
                Text(
                    text = meal.name ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()
                Text(
                    text = meal.description ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
