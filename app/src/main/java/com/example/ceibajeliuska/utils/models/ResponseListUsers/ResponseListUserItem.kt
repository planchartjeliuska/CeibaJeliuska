package com.example.ceibajeliuska.utils.models.ResponseListUsers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class ResponseListUserItem(

    val email: String,
    @PrimaryKey()
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)