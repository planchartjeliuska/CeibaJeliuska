package com.example.ceibajeliuska.data.repositories

import android.app.Application
import com.example.ceibajeliuska.data.local.DatabaseCeiba
import com.example.ceibajeliuska.data.local.dao.UsersDao
import com.example.ceibajeliuska.data.retrofit.RetrofitService
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUser
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUserItem

class Repository constructor(
    private val retrofitService: RetrofitService,
    private val application: Application
) {

    private val usersDao: UsersDao? = DatabaseCeiba.getInstance(application)?.usersDao()

    fun getAllUsers() = retrofitService.getAllUsers()

    fun getAllPostByUser(userId:Int) = retrofitService.getAllPostByUser(userId)

    suspend fun saveAllUsers(body: ResponseListUser) {
        usersDao?.saveAllUsers(body)
    }

    suspend fun getAllUsersLocally():List<ResponseListUserItem>?{
        return usersDao?.getAllUsers()
    }

    suspend fun getUserById(userId: Int):ResponseListUserItem?{
        return usersDao?.getUserById(userId)
    }
}