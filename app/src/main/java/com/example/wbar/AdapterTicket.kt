package com.example.wbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wbar.model.ObjApp
import com.example.wbar.model.TicketApp
import kotlinx.android.synthetic.main.carditem.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterTicket(var mTicketApp: CardItem) : RecyclerView.Adapter<AdapterTicket.TaskViewHolder>(){

    private var datalist = emptyList<TicketApp>()

    fun updateList(mDataList: List<TicketApp>){
        datalist = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTicketText      = itemView.tvCIid
        val itemTicketText    = itemView.tvCIproduct
        val totalTicketText    = itemView.tvCItotal

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent,
            false
        )
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val mTicketApp : TicketApp = datalist[position]
        holder.idTicketText.text = mTicketApp.id.toString()
        holder.itemTicketText.text = mTicketApp.txt
        holder.totalTicketText.text = mTicketApp.total.toString()

    }

    override fun getItemCount() = datalist.size

}