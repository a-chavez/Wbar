package com.example.wbar.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


private const val DATA_BASE_NAME = "task_db"
@Database (entities = [ObjApp::class,TicketApp::class],version = 1)
abstract class ConectorDB:RoomDatabase() {

    abstract fun getObjDao(): ObjDAO
    abstract fun getTicketDao(): TicketDAO

    companion object{
        @Volatile
        private var INSTANCE: ConectorDB? = null

        fun getDataBase(context: Context): ConectorDB {
            val tempInstance = INSTANCE
            if(tempInstance !=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, ConectorDB::class.java, DATA_BASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}