package com.example.wbar.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wbar.CardItem
import kotlinx.coroutines.launch

class ZViewModel(application: Application) : AndroidViewModel(application) {

   private val mRepository: ZRepo
   val mAllObj: LiveData<List<ObjApp>>
   val mAllTicket: LiveData<List<TicketApp>>

    init{
        val mObjDAO = ConectorDB.getDataBase(application).getObjDao()
        val mTicketDAO = ConectorDB.getDataBase(application).getTicketDao()
        mRepository = ZRepo(mObjDAO, mTicketDAO)
        mAllObj = mRepository.mListAllObjApp
        mAllTicket = mRepository.mListAllTicketApp
    }

    fun insertObj (mOBj : ObjApp) = viewModelScope.launch {
        mRepository.insertObj(mOBj)
    }

    fun insertTicket (mTicket : TicketApp) = viewModelScope.launch {
        mRepository.insertTicket(mTicket)
    }

    fun deleteAllObj () = viewModelScope.launch {
        mRepository.deleteAllObj()
    }

    fun deleteAllTicket () = viewModelScope.launch {
        mRepository.deleteAllTicket()
    }

    fun getOneObjByID(id:Int) : LiveData<ObjApp> {
        return mRepository.getOneObjbyID(id)
    }

    fun getOneTicketByID(id:Int) : LiveData<TicketApp> {
        return mRepository.getOneTicketbyID(id)
    }

    fun updateObj(mOBj: ObjApp) = viewModelScope.launch {
        mRepository.updateObject(mOBj)
    }

    fun updateTicket(mTicket: TicketApp) = viewModelScope.launch {
        mRepository.updateTicket(mTicket)
    }

}