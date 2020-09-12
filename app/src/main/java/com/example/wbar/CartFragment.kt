package com.example.wbar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wbar.model.ZViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel: ZViewModel
    var bID : Int? =null
    var ctd = 1
    var tot = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this).get(ZViewModel::class.java)
        arguments?.let {
            bID = it.getInt("ID")
            Log.d("Atencion",bID.toString())
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
                tot = it.price*ctd
                tvPubName.setText(it.pub)
                tvItem.setText(it.product)
                tvUnit.setText(it.unit)
                tvPrice.setText(it.price.toString())
                tvCtd.setText(ctd.toString())
                tvTotal.setText(tot.toString())
                Glide.with(this).load(it.img).into(imgItem)

            })
        }

        btUp.setOnClickListener {
            ctd++
            tvTotal.setText((tot*ctd).toString())
            tvCtd.setText(ctd.toString())

        }

        btDown.setOnClickListener {
            if (ctd > 1) {
                ctd--
                tvTotal.setText((tot * ctd).toString())
                tvCtd.setText(ctd.toString())
            }
        }

        btHome.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}