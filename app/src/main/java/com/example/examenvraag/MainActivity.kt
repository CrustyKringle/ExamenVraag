package com.example.examenvraag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examenvraag.ui.theme.ExamenVraagTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryList()
        }
    }
}

@Composable
fun CountryButton(countryName: String, onClick: (String) -> Unit){
    Button(
        onClick = { onClick(countryName)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ){
        Text(text = countryName)
    }
}

@Composable
fun FlagGrid(colors: List<Color>){
    Column {
        for (i in 0 until 3){
            Row{
                for(j in 0 until 3) {
                    val colorIndex = i * 3 + j
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(colors[colorIndex])
                    )
                }
            }
        }
    }
}

@Composable
fun CountryList() {

    val countries = listOf("België","Frankrijk","Nederland")

    var selectedCountry by remember { mutableStateOf<String?>(null) }

    val countryFlags = mapOf(
        "België" to listOf(
            Color.Black, Color.Yellow, Color.Red,
            Color.Black, Color.Yellow, Color.Red,
            Color.Black, Color.Yellow, Color.Red
        ),
        "Frankrijk" to listOf(
            Color.Blue, Color.White, Color.Red,
            Color.Blue, Color.White, Color.Red,
            Color.Blue, Color.White, Color.Red
        ),
        "Nederland" to listOf(
            Color.Red, Color.Red, Color.Red,
            Color.White, Color.White, Color.White,
            Color.Blue, Color.Blue, Color.Blue
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ){
        countries.forEach{ country ->
            CountryButton(countryName = country) {
                selectedCountry = country
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedCountry?.let{
            Text(text = "Vlag van $it")
            Spacer(modifier = Modifier.height(16.dp))
            FlagGrid(colors = countryFlags[it] ?: emptyList())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    CountryList()
}