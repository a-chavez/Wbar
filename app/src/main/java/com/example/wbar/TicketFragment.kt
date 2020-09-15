package com.example.wbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbar.model.ZViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_ticket.*

class TicketFragment : Fragment() {

    lateinit var viewModel: ZViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ZViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRec = recyclerViewTicket
        val mAdapterTicket = AdapterTicket()
        mRec.adapter = mAdapterTicket
        mRec.layoutManager = LinearLayoutManager(context)

        viewModel.mAllTicket.observe(viewLifecycleOwner,  {
            mAdapterTicket.updateList(it)
        })

        btInicioTicket.setOnClickListener {
            findNavController().navigate(R.id.action_ticketFragment_to_FirstFragment)
        }
    }
}