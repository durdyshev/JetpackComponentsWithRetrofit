package com.example.jetpackcomponentswithretrofit.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomponentswithretrofit.model.PhotoData
import com.example.jetpackcomponentswithretrofit.repository.JsonPlaceHolderRepository
import com.example.jetpackcomponentswithretrofit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: JsonPlaceHolderRepository
):ViewModel() {
    val photoList= MutableLiveData<List<PhotoData>>()
    var errorMessage= mutableStateOf("")
    fun loadPhoto(){
        viewModelScope.launch {
            val result=repository.getPhotoList()
            when(result){
                is Resource.Success->{
                    val photoItems=result.data!!.mapIndexed{index,photoList->
                        PhotoData(photoList.albumId,photoList.id,photoList.title,
                        photoList.url,photoList.thumbnailUrl)
                    }
                    errorMessage.value=""
                    photoList.value=photoItems
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                }
                else -> {

                }
            }
        }
    }
}