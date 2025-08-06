package com.example.hospitalapp.ui.theme.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hospitalapp.R
import com.example.hospitalapp.navigation.ROUTE_ADD_PATIENT
import com.example.hospitalapp.navigation.ROUTE_LOGIN
import com.example.hospitalapp.navigation.ROUTE_VIEW_PATIENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DashboardScreen(navController: NavController){
    val selectedItem = remember { mutableStateOf(1) }
   Scaffold ( bottomBar = {
       NavigationBar (containerColor = Color.Cyan){
           NavigationBarItem(
               selected = selectedItem.value == 0,
               onClick = {},
               icon = { Icon(Icons.Filled.Share, contentDescription = "share")},
               label = { Text(text = "Share")},
               colors = NavigationBarItemDefaults.colors(
                   selectedIconColor = Color.Blue,
                   unselectedIconColor = Color.Blue,

               ),
               alwaysShowLabel = true

               )
           NavigationBarItem(
               selected = selectedItem.value == 1,
               onClick = {},
               icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                label = { Text(text = "Home") },
               alwaysShowLabel = true


           )
           NavigationBarItem(
               selected = selectedItem.value == 2,
               onClick = {},
               icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
               label = { Text(text = "Email") },
               alwaysShowLabel = true
           )

       }
   } )
   { innerPadding ->
       Box(modifier = Modifier.padding(innerPadding)){
            Image(
                painter = painterResource(id=R.drawable.moon),
                contentDescription = "Background image",
                contentScale = ContentScale.FillBounds
                )
       }
       Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
           TopAppBar(
               title = {Text("Hospital")},
               navigationIcon = {
                   IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                   }
               },

               actions = {
                   IconButton(onClick = {}) {
                       Icon(Icons.Filled.Search, contentDescription = "Search")
                   }
                   IconButton(onClick = {}) {
                       Icon(Icons.Filled.Person, contentDescription = "Person")
                   }
                   IconButton(onClick = {}) {
                       Icon(Icons.Filled.AccountCircle, contentDescription = "Logout")
                   }
               },

               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.Cyan,
                   titleContentColor = Color.DarkGray,
                   navigationIconContentColor = Color.DarkGray,
                   actionIconContentColor = Color.DarkGray
               )
               )
           Column (modifier = Modifier.height(400.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

               Text(
                   "welcome to something.. Hospital",
                   fontSize = 23.sp,
//                   fontWeight = FontWeight.Bold,
                   fontStyle = FontStyle.Normal,
                   color = Color.Cyan

               )
               Spacer(modifier = Modifier.height(30.dp))

               Row (modifier = Modifier.wrapContentWidth()){

                   Card(
                       modifier = Modifier.clickable {navController.navigate(ROUTE_ADD_PATIENT) },
                       shape = RoundedCornerShape(15.dp),
                       elevation = CardDefaults.cardElevation(10.dp),
                       colors = CardDefaults.cardColors(Color.White)
                   ) {
                       Box(
                           modifier = Modifier.height(110.dp).padding(20.dp),
                           contentAlignment = Alignment.Center){
                           Column(horizontalAlignment = Alignment.CenterHorizontally) {
                               Icon(Icons.Default.Person, contentDescription = "person", tint = Color.Blue)
                               Text(
                                   "Add patient ",
                                   fontSize = 20.sp,
                                   fontWeight = FontWeight.Bold,
                                   color = Color.Black

                               )
                           }

                       }
                   }
                   Spacer(modifier = Modifier.width(20.dp))
                   Card(
                       modifier = Modifier.clickable {navController.navigate(ROUTE_VIEW_PATIENT) },
                       shape = RoundedCornerShape(15.dp),
                       elevation = CardDefaults.cardElevation(10.dp),
                       colors = CardDefaults.cardColors(Color.White)
                   ) {
                       Box(
                           modifier = Modifier.height(110.dp).padding(20.dp),
                           contentAlignment = Alignment.Center){
                           Column(horizontalAlignment = Alignment.CenterHorizontally) {
                               Icon(Icons.Default.AccountCircle, contentDescription = "person", tint = Color.Blue)
                               Text(
                                   "View patient ",
                                   fontSize = 20.sp,
                                   fontWeight = FontWeight.Bold,
                                   color = Color.Black

                               )
                           }
                       }
                   }
                   Spacer(modifier = Modifier.height(70.dp))
               }


           }


//           Row (modifier = Modifier.wrapContentWidth()){
//               Card(
//                   modifier = Modifier.clickable {navController.navigate(ROUTE_ADD_PATIENT) },
//                   shape = RoundedCornerShape(15.dp),
//                   elevation = CardDefaults.cardElevation(10.dp),
//                   colors = CardDefaults.cardColors(Color.White)
//               ) {
//                   Box(
//                       modifier = Modifier.height(100.dp).padding(20.dp),
//                       contentAlignment = Alignment.Center){
//                       Text(
//                           "Add patient",
//                           fontSize = 10.sp,
//                           fontWeight = FontWeight.Bold,
//                           color = Color.Blue,
//
//                       )
//
//                   }
//               }
//               Spacer(modifier = Modifier.width(30.dp))
//               Card(
//                   modifier = Modifier.padding(10.dp).clickable { navController.navigate(ROUTE_ADD_PATIENT) },
//                   shape = RoundedCornerShape(15.dp),
//                   elevation = CardDefaults.cardElevation(10.dp),
//                   colors = CardDefaults.cardColors(Color.White)
//               ) {
//                   Box(
//                       modifier = Modifier.height(100.dp).padding(20.dp),
//                       contentAlignment = Alignment.Center){
//                       Text(
//                           "View patients",
//                            fontSize = 10.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Blue
//                       )
//                   }
//               }
//
//
//
//
//           }
       }
   }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen(rememberNavController())
}