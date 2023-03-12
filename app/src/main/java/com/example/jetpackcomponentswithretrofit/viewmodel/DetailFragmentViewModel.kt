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
class DetailFragmentViewModel @Inject constructor(
    private val repository: JsonPlaceHolderRepository
):ViewModel() {
    val photo= MutableLiveData<PhotoData>()
    var errorMessage= mutableStateOf("")
    fun loadPhoto(id:String){
        viewModelScope.launch {
            val result=repository.getPhoto(id)
            when(result){
                is Resource.Success->{
                    val photoData=PhotoData(result.data!!.albumId,
                        result.data.id,
                        result.data.title,
                        result.data.url,
                        result.data.thumbnailUrl,
                    )

                    errorMessage.value=""
                    photo.value=photoData
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