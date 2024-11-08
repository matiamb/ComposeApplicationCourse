package com.example.composeapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun GreetingCard(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        ), label = "DpAnimation"
//    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
        Row(Modifier.padding(24.dp).animateContentSize()) {
            Column(
                Modifier.weight(1F)
            ) {
                Text(
                    text = "Hello",
                    modifier = modifier,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        //aqui puedo overridear el tema y agregar un color que quiera
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                )
                Text(
                    text = "$name!"
                )
                if(expanded){
                    Text(
                        text = stringResource(R.string.card_text_rdr2)
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                if (expanded){
                    Icon(Icons.Filled.ExpandLess, contentDescription = "")
                } else {
                    Icon(Icons.Filled.ExpandMore, contentDescription = "")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposeApplicationTheme {
        Greetings()
    }
}

@Composable
fun MyApp(
    modifier: Modifier
){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
        } else {
            Greetings()
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    ComposeApplicationTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier,
             names: List<String> = List(1000){"$it"}
){
    LazyColumn (modifier) {
        items( items = names){ name ->
            GreetingCard(
                name = name,
                Modifier.fillMaxWidth()
            )
        }
    }
}

//State Hoisting

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick =  onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeApplicationTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}