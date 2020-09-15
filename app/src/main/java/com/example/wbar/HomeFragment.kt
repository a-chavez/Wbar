package com.example.wbar

import android.os.Bundle
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
        tvTicketMain.text = ("Total $${ticket.toString()}")
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.mAllObj.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
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
            findNavController().navigate(R.id.action_FirstFragment_to_cardItem)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}