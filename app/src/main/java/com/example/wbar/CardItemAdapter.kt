package com.example.wbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.carditem.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class CardItemAdapter(var mPassData: PassData) : RecyclerView.Adapter<CardItemAdapter.TaskViewHolder>() {

    private var datalist = emptyList<CardItem>()

    fun updateList(mDataList: List<CardItem>) {
       datalist = mDataList
      //  notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val ciUrl       = itemView.imgCardItem
        val ciProduct   = itemView.tvCIproduct
        val ciUnit      = itemView.tvCIunit
        val ciCtd       = itemView.tvCIctd
        val ciPrice     = itemView.tvCIprice
        val ciTotal     = itemView.tvCItotal

        override fun onClick(p0: View?) {
            mPassData.passData(datalist[adapterPosition])
        }

    }

    interface PassData{
        fun passData (mCardItem: CardItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.carditem,parent,false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}




    /*
    (private val mContext: Context, private val mListCardItem:List<CardItem>) : ArrayAdapter<CardItem>(mContext,0,mListCardItem) {
    constructor(mContext: SecondFragment, mListCardItem: List<CardItem>) : this()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.carditem,parent,false) //me permite "llamar" al layout donde estan los objetos

        // Inicio Contenido del Adaptador
        val item = mListCardItem[position]

        Glide.with(mContext).load(item.cardItemUrl).into(layout.imgCardItem)
        layout.tvCIid.text      = position.toString()
        layout.tvCIproduct.text = item.cardItemProduct
        layout.tvCIunit.text    = item.cardItemUnit
        layout.tvCIctd.text     = item.cardItemCtd.toString()
        layout.tvCIprice.text   = "$${item.cardItemPrice.toString()}"
        layout.tvCItotal.text   = "$${item.cardItemTotal.toString()}"

        // Fin Contenido del Adaptador

        return layout
    }

*/

