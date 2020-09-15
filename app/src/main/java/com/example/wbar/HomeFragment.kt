package com.example.wbar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbar.model.ObjApp
import com.example.wbar.model.ZViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class FirstFragment : Fragment(), Adapter.PassObj {

    lateinit var viewModel: ZViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) //activar menu item
        viewModel = ViewModelProvider(this).get(ZViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView = recyclerView
        val mAdapter = Adapter(this)
        tvTicketMain.text = ("Total $${ticket}")
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.mAllObj.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            if(mAdapter.itemCount==0) dummyData()
        })

    }

    override fun passData(mObjApp: ObjApp) {
        val b = Bundle()
        b.putInt("ID",mObjApp.id)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,b)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menuMaster) {
            findNavController().navigate(R.id.action_FirstFragment_to_masterProduct)
            return true
        }

        if (id == R.id.menuConsumo) {
           findNavController().navigate(R.id.action_FirstFragment_to_ticketFragment)
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    fun dummyData(){
        Log.d("Arroz", "dummyData ")
        val pub1 = "El Antro de Juan Jose"
        val pub2 = "Sushi tumaire"
        val pub3 = "Fonda Apruebamelo"

        val mData1 = ObjApp(
            pub = pub1,
            product = "Whisky Ballantines",
            unit = "Vaso + Bebida",
            price = 5500,
            img = "https://static.wixstatic.com/media/6c8ed8_c739fa26d77f430fb5a2c5739ade5f8d.jpg"
        )
        val mData2 = ObjApp(
            pub = pub1,
            product = "Whisky Chivas 12 Años",
            unit = "Vaso",
            price = 8000,
            img = "https://st4.depositphotos.com/2178707/21945/i/450/depositphotos_219451634-stock-photo-chivas-regal-whiskey-bottle-glass.jpg"
        )
        val mData3 = ObjApp(
            pub = pub1,
            product = "Whisky Chivas 18 Años",
            unit = "Vaso",
            price = 10000,
            img = "https://http2.mlstatic.com/D_NQ_NP_928509-MLA27716940538_072018-O.jpg"
        )
        val mData4 = ObjApp(
            pub = pub1,
            product = "Jack Daniels Old N°7",
            unit = "Vaso",
            price = 12000,
            img = "https://chefandhotel.cl/images/ediciones/2020_03/cocteles_jack_daniels/Cocteleria-Jack-Daniels-chefandhotel-PRINCIPAL-INTERIOR.jpg"
        )
        val mData5 = ObjApp(
            pub = pub2,
            product = "Shop Cristal",
            unit = "Mediano",
            price = 2500,
            img = "https://pbs.twimg.com/media/DQ9Eqo6X4AAkHbe.jpg"
        )
        val mData6 = ObjApp(
            pub = pub2,
            product = "Shop Kunstmann",
            unit = "Mediano",
            price = 4000,
            img = "http://adm.1.cl/galeriasitios/Och/2017/2/15/Och_20209_Fl-10505-HaussmannParqueArauco-Fg-1-05.jpg"
        )
        val mData7 = ObjApp(
            pub = pub2,
            product = "Cerveza Austral Variedades",
            unit = "330cc",
            price = 4000,
            img = "https://revistasml.cl/wp-content/uploads/2018/02/Austral.jpg"
        )
        val mData8 = ObjApp(
            pub = pub2,
            product = "Cerveza Heineken",
            unit = "330cc",
            price = 2500,
            img = "https://http2.mlstatic.com/cerveza-heineken-con-vaso-D_NQ_NP_648672-MLC40168044553_122019-F.jpg"
        )
        val mData9 = ObjApp(
            pub = pub3,
            product = "Vaso de Chicha",
            unit = "Caña",
            price = 1500,
            img = "https://holapacha.files.wordpress.com/2015/09/chicha-portada.jpg"
        )
        val mData10 = ObjApp(
            pub = pub3,
            product = "Jarra de Chicha",
            unit = "Litro",
            price = 4000,
            img = "https://www.fortinmapocho.cl/wp-content/uploads/2017/04/IMG_3763.jpg"
        )
        val mData11 = ObjApp(
            pub = pub3,
            product = "Terremoto",
            unit = "500cc",
            price = 2500,
            img = "https://upload.wikimedia.org/wikipedia/commons/0/05/Terremotopiojera.jpg"
        )
        viewModel.insertObj(mData1)
        viewModel.insertObj(mData2)
        viewModel.insertObj(mData3)
        viewModel.insertObj(mData4)
        viewModel.insertObj(mData5)
        viewModel.insertObj(mData6)
        viewModel.insertObj(mData7)
        viewModel.insertObj(mData8)
        viewModel.insertObj(mData9)
        viewModel.insertObj(mData10)
        viewModel.insertObj(mData11)
    }



}