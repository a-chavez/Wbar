package com.example.wbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wbar.model.ObjApp
import kotlinx.android.synthetic.main.item_list.view.*

class Adapter(var mPassObj: PassObj) : RecyclerView.Adapter<Adapter.TaskViewHolder>(){

    private var datalist = emptyList<ObjApp>()

    fun updateList(mDataList: List<ObjApp>){
        datalist = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val idText       = itemView.tvId
        val itemText    = itemView.tvItem
        val unitText    = itemView.tvUnit
        val priceText   = itemView.tvPrice
        val pubText     = itemView.tvPub
        val img         = itemView.imgDrink
        val itemView    = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            mPassObj.passData(datalist[adapterPosition])

        }
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
        val mObjApp : ObjApp = datalist[position]
        holder.idText.text = mObjApp.id.toString()
        holder.itemText.text = mObjApp.product
        holder.unitText.text = mObjApp.unit
        holder.priceText.text = mObjApp.price.toString()
        holder.pubText.text = mObjApp.pub
        Glide.with(holder.itemView.context).load(mObjApp.img).into(holder.img)
    }

    override fun getItemCount() = datalist.size

    interface PassObj{
        fun passData(mObjApp: ObjApp)
    }
}