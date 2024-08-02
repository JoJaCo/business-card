package com.example.business_card

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.business_card.ui.theme.BusinesscardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinesscardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}
//the card overall
@Composable
fun CreateBizCard(){
    var buttonClickedState = remember {
        mutableStateOf(value = false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.Gray),
            elevation = CardDefaults.cardElevation(10.dp)) {


            Column(modifier = Modifier.height(700.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImageProfile()
                Divider()
                ClientInfo()
                Button(
                    onClick = {
                              buttonClickedState.value = !buttonClickedState.value


                    },
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .size(width = 150.dp, height = 40.dp),
                    shape = CutCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                ) {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.bodyMedium)

                }
                if (buttonClickedState.value){
                    Content()
                }else{
                    Box() {

                    }
                }

            }
        }


    }

}
//data of the clients
@Preview
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)

        ) {

            Portfolio(data = listOf("Project1","Project2","Project3"))

        }
    }

}
//Column of the data
@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{items(data){ item ->
        Card(modifier = Modifier
            .padding(13.dp)
            .fillMaxWidth(),
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(4.dp),){
            Row(modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(7.dp)){
                CreateImageProfile(modifier = Modifier.size(100.dp))
                Column(modifier = Modifier
                    .padding(7.dp)
                    .align(alignment = Alignment.CenterVertically)) {

                    Text(text = item, fontWeight = FontWeight.Bold)
                    Text(text = "A great Project", style = MaterialTheme.typography.bodySmall)
                }
            }

            }
        }
    }
}



//info of the client
@Composable
private fun ClientInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = "Jorge Contreras", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Android Compose Programmer", fontSize = 20.sp)
        Text(text = "@joji.ai", color = Color.Blue, fontSize = 20.sp)
    }
}
//for the image
@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.DarkGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "user picture",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreateBizCard()
}