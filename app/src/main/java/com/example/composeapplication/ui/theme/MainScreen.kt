package com.example.composeapplication.ui.theme



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(text: String){
    GreetingCard(text)
}

@Composable
private fun GreetingCard(text: String){
    Card(modifier = Modifier
        .padding(16.dp)) {
        Text(text, modifier = Modifier.padding(16.dp))
    }
}