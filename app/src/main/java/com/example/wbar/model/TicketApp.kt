package com.example.wbar.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_Tickets")
data class TicketApp (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int =0,
    val txt:String,
    val total:Int
)
