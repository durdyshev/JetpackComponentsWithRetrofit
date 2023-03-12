package com.example.jetpackcomponentswithretrofit.repository

import com.example.jetpackcomponentswithretrofit.model.PhotoData
import com.example.jetpackcomponentswithretrofit.service.PlaceHolderApi
import com.example.jetpackcomponentswithretrofit.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonPlaceHolderRepository @Inject constructor(
    private val api:PlaceHolderApi
){
    suspend fun getPhotoList(): Resource<List<PhotoData>>{
        val response=try{
            api.getPhotoList()
        }catch (e:Exception){
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }
    suspend fun getPhoto(id:String): Resource<PhotoData>{
        val response=try{
            api.getPhoto(id)
        }catch (e:Exception){
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }
}