package com.example.wbar.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "table_Products")
data class ObjApp (
    @PrimaryKey (autoGenerate = true)
    @NonNull
    val id: Int =0,
    val product: String,
    val unit:String,
    val price:Int,
    val pub:String,
    val img:String
)
