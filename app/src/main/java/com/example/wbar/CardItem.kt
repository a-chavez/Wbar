package com.example.wbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbar.model.ZViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class CardItem : Fragment() {
    lateinit var viewModel: ZViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ZViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRec = recyclerView
        val mAdapterTicket = AdapterTicket(this)
        mRec.adapter = mAdapterTicket
        mRec.layoutManager = LinearLayoutManager(context)

        viewModel.mAllTicket.observe(viewLifecycleOwner, Observer {
            mAdapterTicket.updateList(it)
        })

    }
}
