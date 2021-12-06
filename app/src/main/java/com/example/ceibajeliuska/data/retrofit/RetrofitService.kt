package com.example.ceibajeliuska.data.retrofit

import com.example.ceibajeliuska.utils.models.ResponseListPosts.ResponseListPostsItem
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    companion object{

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

    @GET ("/users")
    fun getAllUsers  (): Call<ResponseListUser>

    @GET ("/posts")
    fun getAllPostByUser(@Query("userId") userId:Int): Call<List<ResponseListPostsItem>>


}