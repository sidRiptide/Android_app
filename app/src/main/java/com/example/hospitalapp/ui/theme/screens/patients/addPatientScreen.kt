package com.example.hospitalapp.ui.theme.screens.patients

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.hospitalapp.R
import com.example.hospitalapp.data.PatientViewModel
import com.example.hospitalapp.navigation.ROUTE_DASHBOARD

@Composable
fun AddPatientScreen(navController: NavController){
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var  age by remember { mutableStateOf("") }
    var  diagnosis by remember { mutableStateOf("") }
    var phonenumber by remember { mutableStateOf ("") }
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
//   what the hell is this?
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        uri:Uri? -> uri?.let { imageUri.value=it }
    }
    val patientViewModel:PatientViewModel = viewModel()
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxSize().padding(20.dp),  horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "ADD NEW PATIENT",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = Color.Magenta,
            modifier = Modifier.fillMaxWidth()

        )
        Card(shape = CircleShape, modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(
                model = imageUri.value ?: R.drawable.ic_person_edit1,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clickable { launcher.launch("image/*") }
                )
        }
        Text("Upload Picture here")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("enter Patient name") },
            placeholder = { Text("please enter patient name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("enter Gender") },
            placeholder = { Text("please enter patient's gender") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text("enter Patient's nationality") },
            placeholder = { Text("please enter patient's nationality") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phonenumber,
            onValueChange = { phonenumber = it },
            label = { Text("Phonenumber") },
            placeholder = { Text("phonenumber") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("enter Patient Age") },
            placeholder = { Text("please enter patient age") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = diagnosis,
            onValueChange = { diagnosis = it },
            label = { Text("Diagnosis") },
            placeholder = { Text("Diagnosis") },
            modifier = Modifier.fillMaxWidth().height(100.dp),
            singleLine = false
        )
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = {navController.navigate(ROUTE_DASHBOARD)}) { Text(" <- Go Back") }
//            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = { patientViewModel.uploadPatient(imageUri.value,name,gender,nationality, phonenumber,age,diagnosis,context)},
                colors = ButtonDefaults.buttonColors(Color.Green)) { Text("SAVE") }
        }

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddPatientScreenPreview(){
    AddPatientScreen(rememberNavController())
}