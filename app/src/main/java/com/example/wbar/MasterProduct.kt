package com.example.wbar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wbar.model.ObjApp
import com.example.wbar.model.ZViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_master_product.*

class MasterProduct : Fragment() {

    lateinit var mViewModel: ZViewModel
    var bID : Int? =null
    var uri = "https://cdn.pixabay.com/photo/2013/11/12/01/29/bar-209148_960_720.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel= ViewModelProvider(this).get(ZViewModel::class.java)


        arguments?.let {
            bID = it.getInt("ID")
            Log.d("Atencion",bID.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_master_product,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(uri)
            .into(imgMaster)

        bID?.let{
            mViewModel.getOneObjByID(it).observe(viewLifecycleOwner, Observer {
                Log.d("Atencion encontre", it.id.toString())
                etMasterPub.setText(it.pub)
                etMasterProduct.setText(it.product)
                etMasterUnit.setText(it.unit)
                etMasterPrice.setText(it.price.toString())
                etMasterUrl.setText(it.img)
            })
        }


        btGrabar.setOnClickListener {

            val pubTxt      = etMasterPub.text.toString()
            val productTxt  = etMasterProduct.text.toString()
            val unitTxt     = etMasterUnit.text.toString()
            val priceTxt    = etMasterPrice.text.toString()
            var urlTxt      = etMasterUrl.text.toString()

            if (pubTxt.isNotEmpty() && productTxt.isNotEmpty() && unitTxt.isNotEmpty() && priceTxt.isNotEmpty()) {
                if (urlTxt.isEmpty()) urlTxt = uri

               val mObjApp = ObjApp(pub = pubTxt, product = productTxt, unit = unitTxt, price = priceTxt.toInt(),img = urlTxt)
               mViewModel.insertObj(mObjApp)

                etMasterPub.setText("")
                etMasterProduct.setText("")
                etMasterUnit.setText("")
                etMasterPrice.setText("")
                etMasterUrl.setText(uri)

                Snackbar.make(view, "Producto agregado", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            } else {
                Snackbar.make(view, "Los campos no pueden estar Vacios", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
        }

        btInicio.setOnClickListener {
            findNavController().navigate(R.id.action_masterProduct_to_FirstFragment)
        }
    }
}