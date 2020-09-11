package com.example.wbar.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ObjDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(mObjApp: ObjApp)

    @Update
    suspend fun updateOne(mObjApp: ObjApp)

    @Query("SELECT * FROM table_Products ORDER BY id")
    fun getAllObj(): LiveData<List<ObjApp>>

    @Query("SELECT * FROM table_Products WHERE id=:mId")
    fun getOneObj(mId: Int): LiveData<ObjApp>

    @Query("DELETE FROM table_Products")
    suspend fun deleteAll()

}
