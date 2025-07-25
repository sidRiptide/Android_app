package com.example.hospitalapp.ui.theme.screens.register
import  androidx.compose. *
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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

@Composable
fun registerScreen(navController: NavController){
//    declaring variables in the outlinedTextField
    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val authViewModel:AuthViewModel = viewModel()
//    column=arranges fields in it in a column !!So obvious bro!!
    Box(){
        Image(
            painter = painterResource(id = R.drawable.bit),
            contentDescription = "registerBackground",
            contentScale = ContentScale.FillBounds
        )
    }
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Register here",
            fontSize = 40.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
            )
//        basically spaces the contents in between
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
//        painter is what we use to call the image (i think),R ni ya url(i think)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Image Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentScale = ContentScale.Fit
            )
//        acts as our form no wonder we need a value which will be fetched and posted to database
        OutlinedTextField(
            value = username,
            onValueChange = {username=it},
            label = { Text("Enter Username")},
            textStyle = TextStyle(color = Color.Blue),
            placeholder = { Text("Okarun") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
            modifier = Modifier.fillMaxWidth(0.8f)

        )
        OutlinedTextField(
            value = fullname,
            onValueChange = {fullname=it},
            label = { Text("Enter fullname")},
            placeholder = { Text("Jin Kazama") },
            textStyle = TextStyle(color = Color.Blue),
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "fullname Icon") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = { Text("Enter email")},
            placeholder = { Text("Okarun@gmail.com") },
            textStyle = TextStyle(color = Color.Blue),
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
            modifier = Modifier.fillMaxWidth(0.8f)

        )
        OutlinedTextField(
            value = password,
            onValueChange = {password=it},
            label = { Text("Enter password")},
            placeholder = { Text("") },
            textStyle = TextStyle(color = Color.Blue),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {confirmPassword=it},
            label = { Text("confirm password")},
            placeholder = { Text("") },
            textStyle = TextStyle(color = Color.Blue),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        val context = LocalContext.current
        Button(
            onClick ={authViewModel.signup(username= username,email=email, fullname=fullname,password=password,confirmpassword=confirmPassword,navController=navController,context=context)},
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier.fillMaxWidth(0.8f) )
        { Text("Register", color = Color.Green) }

        Text(
            "if already registered.Login here",
            modifier = Modifier.clickable {navController.navigate(ROUTE_LOGIN) },
            color = Color.Blue
        )

    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun registerScreenPreview(){
    registerScreen(rememberNavController())
}