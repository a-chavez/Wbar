package com.example.wbar.model

import androidx.lifecycle.LiveData


class ZRepo (private val mObjDAO: ObjDAO, private val mTicketDAO: TicketDAO){

    val mListAllObjApp      : LiveData<List<ObjApp>> = mObjDAO.getAllObj()
    val mListAllTicketApp   : LiveData<List<TicketApp>> = mTicketDAO.getAllTicket()

    suspend fun insertObj(mObj: ObjApp){
        mObjDAO.insertOne(mObj)
    }

    suspend fun insertTicket(mTicket: TicketApp){
        mTicketDAO.insertOne(mTicket)
    }

    suspend fun deleteAllObj(){
        mObjDAO.deleteAll()
    }

    suspend fun deleteAllTicket(){
        mTicketDAO.deleteAllTicket()
    }

    fun getOneObjbyID(id: Int) : LiveData<ObjApp> {
        return mObjDAO.getOneObj(id)
    }

    fun getOneTicketbyID(id: Int) : LiveData<TicketApp> {
        return mTicketDAO.getOneTicket(id)
    }

    fun getLastOneTicket() : LiveData<TicketApp> {
        return mTicketDAO.getLastOneTicket()
    }

    suspend fun updateObject (mObj: ObjApp){
        mObjDAO.updateOne(mObj)
    }

    suspend fun updateTicket (mTicket: TicketApp){
        mTicketDAO.updateOne(mTicket)
    }
}