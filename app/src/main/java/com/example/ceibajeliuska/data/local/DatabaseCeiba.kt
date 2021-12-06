package com.example.ceibajeliuska.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ceibajeliuska.data.local.dao.UsersDao
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUserItem

@Database(
    entities = [
        ResponseListUserItem::class
    ],
    version = 1
)
abstract class DatabaseCeiba : RoomDatabase(){

    abstract fun usersDao(): UsersDao

    companion object {
        private const val DATABASE_NAME = "ceiba_database"
        @Volatile
        private var INSTANCE: DatabaseCeiba? = null

        fun getInstance(context: Context): DatabaseCeiba? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseCeiba::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}