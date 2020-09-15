package com.example.wbar.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TicketDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(mTicketApp: TicketApp)

    @Update
    suspend fun updateOne(mTicketApp: TicketApp)

    @Query("SELECT * FROM table_Tickets ORDER BY id")
    fun getAllTicket(): LiveData<List<TicketApp>>

    @Query("SELECT * FROM table_Tickets WHERE id=:mId")
    fun getOneTicket(mId: Int): LiveData<TicketApp>

    @Query("SELECT * FROM table_Tickets ORDER BY id DESC LIMIT 1")
    fun getLastOneTicket(): LiveData<TicketApp>

    @Query("DELETE FROM table_Tickets")
    suspend fun deleteAllTicket()
}