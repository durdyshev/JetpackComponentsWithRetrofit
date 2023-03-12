package com.example.jetpackcomponentswithretrofit.service

import com.example.jetpackcomponentswithretrofit.model.PhotoData
import com.example.jetpackcomponentswithretrofit.model.PostData
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderApi {
    @GET("posts/")
    suspend fun getPostList():List<PostData>

    @GET("photos/")
    suspend fun getPhotoList():List<PhotoData>

    @GET("photos/{id}")
    suspend fun getPhoto(
       @Path("id") id:String
    ):PhotoData
}