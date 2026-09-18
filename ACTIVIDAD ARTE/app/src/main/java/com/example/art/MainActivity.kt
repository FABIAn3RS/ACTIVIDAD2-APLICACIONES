package com.example.art

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art.ui.theme.ARTTheme

data class Artwork(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

val sampleArtworks = listOf(
    Artwork(
        id = 1,
        imageRes = R.drawable.whatsapp_image_2026_09_17_at_11_39_05_pm,
        title = "El Pirata Perplejo",
        artist = "Galería de Memes",
        year = "2021"
    ),
    Artwork(
        id = 2,
        imageRes = R.drawable.whatsapp_image_2026_09_17_at_11_39_05_pm__1_,
        title = "UNSC Arctic Burn Wasp",
        artist = "Halo Mega Construx",
        year = "2022"
    ),
    Artwork(
        id = 3,
        imageRes = R.drawable.whatsapp_image_2026_09_17_at_11_39_06_pm,
        title = "Master Chief Táctico",
        artist = "Colección Spartan",
        year = "2023"
    ),
    Artwork(
        id = 4,
        imageRes = R.drawable.whatsapp_image_2026_09_17_at_11_39_06_pm__1_,
        title = "Invasión Flood en la Grúa",
        artist = "Diorama Halo Universe",
        year = "2024"
    ),
    Artwork(
        id = 5,
        imageRes = R.drawable.whatsapp_image_2026_09_17_at_11_39_06_pm__2_,
        title = "Spartan John-117 (Arma Tiburón)",
        artist = "Edición Especial Mega",
        year = "2020"
    ),
    Artwork(
        id = 6,
        imageRes = R.drawable.whatsapp_image_2026_09_17_at_11_39_06_pm__3_,
        title = "Messi Chiquito",
        artist = "Lionel Messi (FC Barcelona)",
        year = "2009"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(
                        artworks = sampleArtworks,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(
    artworks: List<Artwork>,
    modifier: Modifier = Modifier
) {
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    val onNextClick = {
        currentIndex = if (currentIndex < artworks.size - 1) {
            currentIndex + 1
        } else {
            0
        }
    }

    val onPreviousClick = {
        currentIndex = if (currentIndex > 0) {
            currentIndex - 1
        } else {
            artworks.size - 1
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ArtworkWall(
            artwork = currentArtwork,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ArtworkDescriptor(
            artwork = currentArtwork,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DisplayController(
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ArtworkWall(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(width = 1.dp, color = Color.Gray)
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = artwork.imageRes),
            contentDescription = artwork.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(8.dp),
        color = Color(0xFFEEEEEE)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = artwork.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(artwork.artist)
                    }
                    append(" (${artwork.year})")
                },
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ARTTheme {
        ArtSpaceScreen(artworks = sampleArtworks)
    }
}