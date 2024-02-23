package com.example.filmspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmspace.ui.theme.FilmSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmSpaceApp()
                }
            }
        }
    }
}

@Composable
fun FilmSpaceApp(){
    var filmIndex by remember {
        mutableIntStateOf(0)
    }

    if(filmIndex == 3) filmIndex = 0
    if(filmIndex == -1) filmIndex = 2

    when(filmIndex){
        0 -> TextAndImageFilm(
            img = R.drawable.harry_potter,
            contentDescription = R.string.harry_potter_poster,
            title = R.string.harry_potter_title,
            rating = R.string.harry_potter_rating,
        )
        1 -> TextAndImageFilm(
            img = R.drawable.hunger_games,
            contentDescription = R.string.hunger_games_poster,
            title = R.string.hunger_games_title,
            rating = R.string.hunger_games_rating
        )
        else -> TextAndImageFilm(
            img = R.drawable.lord_of_the_rings,
            contentDescription = R.string.lord_of_the_rings_poster,
            title = R.string.lord_of_the_rings_title,
            rating = R.string.lord_of_the_rings_rating
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ){
        ButtonFilm(
            onClick = { filmIndex-- },
            text = R.string.button_previous,
            color = R.color.purple_700
        )
        ButtonFilm(
            onClick = { filmIndex++ },
            text = R.string.button_next,
            color = R.color.purple_700
        )
    }

}

@Composable
fun TextAndImageFilm(
    @DrawableRes img: Int,
    @StringRes contentDescription: Int,
    @StringRes title: Int,
    @StringRes rating: Int,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = stringResource(id = contentDescription),
            modifier = modifier.fillMaxWidth().height(250.dp)
        )
        Spacer(
            modifier = modifier.size(32.dp)
        )
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 32.sp
        )
        Text(
            text = stringResource(id = R.string.rating, stringResource(id = rating)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.grey)
        )
    }
}

@Composable
fun ButtonFilm(
    onClick: () -> Unit,
    @StringRes text: Int,
    @ColorRes color: Int,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = color)),
        modifier = modifier.width(140.dp)
    ){
        Text(
            text = stringResource(id = text)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilmSpacePreview() {
    FilmSpaceTheme {
        FilmSpaceApp()
    }
}