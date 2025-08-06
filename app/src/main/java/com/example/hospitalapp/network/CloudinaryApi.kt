package com.example.hospitalapp.network
//
import com.example.hospitalapp.models.CloudinaryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface  CloudinaryApi {
    @Multipart
//    de2gd..blablabla ni cloudname which is pasted from cloudinary
    @POST("v1_1/de2gd7i6g/image/upload>")
    suspend fun uploadImage(
        @Part file:MultipartBody.Part,
        @Part("upload_present") uploadPreset : RequestBody
    ): Response<CloudinaryResponse>
}