package com.example.hospitalapp.data
//
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.models.Patient
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStream


class PatientViewModel:ViewModel(){
    val cloudinaryUrl = "https://api.cloudinary.com/v1_1/de2gd7i6g/image/upload"
    val uploadPreset = "app_images"
        fun uploadPatient(imageUri:Uri?,name:String,gender:String,nationality:String,phonenumber:String,age:String,diagnosis:String,context: Context){

            viewModelScope.launch ( Dispatchers.IO ){
                try {
                    val imageUrl=imageUri?.let { uploadToCloudinary(context,it) }
                    val ref = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("Patients").push()
                    val patientData = mapOf(
                        "id" to ref.key,
                        "name" to name,
                        "gender" to gender,
                        "phonenumber" to phonenumber,
                        "nationality" to nationality,
                        "age" to age,
                        "diagnosis" to diagnosis,
                        "imageUrl" to imageUrl
                    )
                    ref.setValue(patientData).await()
                    withContext(Dispatchers.Main){
                        Toast.makeText(context,"Patient saved Successfully", Toast.LENGTH_LONG).show()
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(context,"Patient not saved", android.widget.Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
    private fun uploadToCloudinary(context: Context,uri: Uri):String {
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes() ?: throw Exception("image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(), fileBytes))
            .addFormDataPart("upload_preset", uploadPreset).build()
        val request = Request.Builder().url(cloudinaryUrl).post(requestBody).build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image Url")
    }
    private val _patients = mutableStateListOf<Patient>()
    val patients:List<Patient> = _patients

    fun fetchPatients(context: Context){
        val ref = FirebaseDatabase.getInstance().getReference("Patients")
        ref.get().addOnSuccessListener { snapshot ->
            _patients.clear()
            for(child in snapshot.children){
                val patient =child.getValue(Patient::class.java)
                patient?.let { _patients.add(it) }
            }
        }.addOnFailureListener{
            Toast.makeText(context,"Failed to load patients",Toast.LENGTH_LONG).show()
        }
    }
    fun deletePatient(patientId:String,context: Context){
        val ref = FirebaseDatabase.getInstance().getReference("Patients").child(patientId)
        ref.removeValue().addOnSuccessListener { _patients.removeAll{it.id == patientId} }
            .addOnFailureListener{
                Toast.makeText(context,"Patients not deleted",Toast.LENGTH_LONG).show()
            }
    }

}