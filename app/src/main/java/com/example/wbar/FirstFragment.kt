package com.example.wbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbar.model.ObjApp
import com.example.wbar.model.ZViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), Adapter.PassObj {

    lateinit var viewModel: ZViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ZViewModel::class.java)
        //dummyData()

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView = recyclerView
        val mAdapter = Adapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.mAllObj.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })

    }

    override fun passData(mObjApp: ObjApp) {
        val b = Bundle()
        b.putInt("ID",mObjApp.id)
        Toast.makeText(context,mObjApp.id.toString(), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,b)
    }

    fun dummyData()  {
        val mObjApp = ObjApp(product = "Piscola Normal",unit = "Vaso",price = 3000,pub = "Pub Sushi tumaire",img = "https://www.diariodecultura.com.ar/wp-content/uploads/2019/05/toptragos.jpg")
        viewModel.insertObj(mObjApp)
    }

}