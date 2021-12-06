package com.example.ceibajeliuska.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ceibajeliuska.utils.models.ResponseListPosts.ResponseListPostsItem
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUserItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel constructor (private val repository: Repository) : ViewModel() {

    val listPost = MutableLiveData<List<ResponseListPostsItem>>()
    val userLocal = MutableLiveData<ResponseListUserItem>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPostByUser (userId:Int){
        val service = repository.getAllPostByUser(userId)

        service.enqueue(object : Callback<List<ResponseListPostsItem>>{
            override fun onResponse(
                call: Call<List<ResponseListPostsItem>>,
                response: Response<List<ResponseListPostsItem>>
            ) {
                if (response.isSuccessful)
                    listPost.postValue(response.body())
                else
                    errorMessage.postValue("Ha ocurrido un error")
            }

            override fun onFailure(call: Call<List<ResponseListPostsItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    fun getLocalUser(userId: Int) {
        viewModelScope.launch {
            userLocal.value = repository.getUserById(userId)
        }
    }
}

class PostViewModelFactory constructor(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            PostViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}