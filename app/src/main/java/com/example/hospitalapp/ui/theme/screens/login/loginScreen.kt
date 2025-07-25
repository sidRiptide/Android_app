package com.example.hospitalapp.ui.theme.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hospitalapp.R
import com.example.hospitalapp.data.AuthViewModel
import com.example.hospitalapp.navigation.ROUTE_LOGIN
import com.example.hospitalapp.navigation.ROUTE_REGISTER

@Composable
fun loginScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authViewModel:AuthViewModel = viewModel()


    Box(){
        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = "registerBackground",
            contentScale = ContentScale.FillBounds
        )

    }
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Login here",
            fontSize = 40.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Image Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentScale = ContentScale.Fit
        )
       OutlinedTextField(
           value = email,
           onValueChange = {email=it},
           label = { Text("email")},
           placeholder = { Text("blablabla@gmail.com")},
           leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
           modifier = Modifier.fillMaxWidth(0.8f)

       )
        OutlinedTextField(
            value = password,
            onValueChange = {password=it},
            label = { Text("password")},
            placeholder = { Text("")},
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
            modifier = Modifier.fillMaxWidth(0.8f)

        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        val context = LocalContext.current
        Button(
            onClick ={
                authViewModel.login(email = email,
                password = password,
                navController=navController,
                context = context)
                     },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier.fillMaxWidth(0.8f) )
        { Text("Login", color = Color.Green) }
        Text(
            "if not registered.Register here",
            modifier = Modifier.clickable {navController.navigate(ROUTE_REGISTER) },
            color = Color.Blue
        )

//        OutlinedTextField(
//            value = username,
//            onValueChange = {username=it},
//            label = { Text("Enter Username")},
//            placeholder = { Text("Okarun") },
//            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
//            modifier = Modifier.fillMaxWidth(0.8f)
//        )

    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreenPreview(){
    loginScreen(rememberNavController())
}