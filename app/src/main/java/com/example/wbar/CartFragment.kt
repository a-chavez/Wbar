package com.example.wbar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wbar.model.ZViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class SecondFragment : Fragment() {

    lateinit var mViewModel: ZViewModel
    var bID : Int? =null
    var ctd = 1
    var tot = 0
    var product = ""
    var unit = ""
    var price = 0
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this).get(ZViewModel::class.java)
        arguments?.let {
            bID = it.getInt("ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bID?.let{
            mViewModel.getOneObjByID(it).observe(viewLifecycleOwner, Observer {
                tot = it.price * ctd
                product = it.product
                unit = it.unit
                price = it.price
                url = it.img

                tvPubName.setText(it.pub)
                tvItem.setText(product)
                tvUnit.setText(unit)
                tvPrice.setText(price.toString())
                tvCtd.setText(ctd.toString())
                tvTotal.setText(tot.toString())
                Glide.with(this).load(url).into(imgItem)
                cardList.adapter = ArrayAdapter(requireContext(), R.layout.mylist, mlist)
                ticket()

            })
        }

        btUp.setOnClickListener {
            ctd++
            tvTotal.setText((tot * ctd).toString())
            tvCtd.setText(ctd.toString())

        }

        btDown.setOnClickListener {
            if (ctd > 1) {
                ctd--
                tvTotal.setText((tot * ctd).toString())
                tvCtd.setText(ctd.toString())
            }
        }

        btAddtoCard.setOnClickListener() {
           ticket+=(ctd*price)

            val mCardItem: String = "$product $unit: $ctd x $$price = $${tot*ctd}"
            addList(mCardItem)
            cardList.adapter = ArrayAdapter(requireContext(), R.layout.mylist, mlist)
            ticket()

            cardList.setOnItemClickListener { parent, view, position, id ->

                    Toast.makeText(requireContext(),"Borrar ${mlist[position]}",Toast.LENGTH_SHORT).show()
                    val parts = mlist[position].split("=".toRegex()).map { it.trim() }
                    val minus:Int = parts[1].substring(1).toInt()
                    ticket-=minus
                    mlist.removeAt(position)
                    cardList.adapter = ArrayAdapter(requireContext(), R.layout.mylist, mlist)
                    ticket()
            }

        }


        btHome.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

 fun ticket()=tvTicket.setText("Total $${ticket.toString()}")

}