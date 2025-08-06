package com.example.hospitalapp.ui.theme.screens.patients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.hospitalapp.data.PatientViewModel
import com.example.hospitalapp.models.Patient

@Composable
fun PatientListScreen(navController: NavController){
    val patientViewModel:PatientViewModel= viewModel()
    val patients = patientViewModel.patients
    val context = LocalContext.current
//   ati wamesema ni loop ndo iloop through data yako

    Column {
        Text("WELCOME")

        LaunchedEffect(Unit) {
            patientViewModel.fetchPatients(context)
        }


        LazyColumn {

            items(patients) { patient ->
                PatientCard(
                    patient,
                    onDelete = { patientId -> patientViewModel.deletePatient(patientId, context) })
            }
        }
    }
}

@Composable
fun PatientCard(patient: Patient,onDelete:(String)-> Unit){
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to Delete this patient?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    patient.id?.let{onDelete(it)}
                }) {
                    Text("Delete", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = {showDialog = false}) {
                    Text("Cancel")
                }
            }

        )
    }

    Card(modifier = Modifier.fillMaxWidth().padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Row(modifier = Modifier.padding(8.dp)){
            patient.imageUrl?.let{imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Patient String",
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(patient.name ?:"No name",style = MaterialTheme.typography.titleMedium)
                Text("AGE: ${patient.age}", style = MaterialTheme.typography.bodySmall)
                Text("DIAGNOSIS: ${patient.diagnosis}",style = MaterialTheme.typography.bodySmall)
            }
        }

        Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
            TextButton(onClick = {}) {
                Text("Update", color = Color.White)
            }
            TextButton(onClick = {showDialog = true}) {
                Text("Delete", color = Color.Red)
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewPatientScreenPreview(){
    PatientListScreen(rememberNavController())
}