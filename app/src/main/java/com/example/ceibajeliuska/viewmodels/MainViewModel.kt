package com.example.ceibajeliuska.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUser
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUserItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor (private val repository: Repository) : ViewModel() {


    val listUser = MutableLiveData<List<ResponseListUserItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUsers (){

        viewModelScope.launch {
            val localUsers = repository.getAllUsersLocally()

            if(!localUsers.isNullOrEmpty()){
                listUser.postValue(localUsers)
            }else{
                val response = repository.getAllUsers()
                response.enqueue(object : Callback<ResponseListUser> {
                    override fun onResponse(call: Call<ResponseListUser>, response: Response<ResponseListUser>) {

                        if (response.isSuccessful && response.body() != null) {
                            listUser.postValue(response.body())
                            saveLocally(response.body()!!)
                        }else{
                            errorMessage.postValue("Ha ocurrido un error con el servicio")
                        }
                    }
                    override fun onFailure(call: Call<ResponseListUser>, t: Throwable) {
                        errorMessage.postValue(t.message)
                    }
                })
            }
        }

    }

    private fun saveLocally(body: ResponseListUser) {
        viewModelScope.launch {
            repository.saveAllUsers(body)
        }
    }
}

class MainViewModelFactory constructor(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {



        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}