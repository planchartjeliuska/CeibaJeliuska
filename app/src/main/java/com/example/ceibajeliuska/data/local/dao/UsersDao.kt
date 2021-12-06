package com.example.ceibajeliuska.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUserItem

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllUsers(users: List<ResponseListUserItem>)

    @Query("select * from users")
    suspend fun getAllUsers(): List<ResponseListUserItem>


    @Query("select * from users where id =:userId")
    suspend fun getUserById(userId: Int): ResponseListUserItem
}