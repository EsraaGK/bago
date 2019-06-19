package com.iti.bago.tabbarActivity.profile.orders

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  com.iti.bago.databinding.OrdersFragmentBinding
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.tabbarActivity.TabbarActivity

class OrdersFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as TabbarActivity).setFragTitle("Orders")

       var binding = DataBindingUtil.inflate<OrdersFragmentBinding>(
            inflater, R.layout.orders_fragment, container, false
        )


        var OrdersFragmentViewModel = ViewModelProviders.of(this).get(
            OrdersFragmentViewModel::class.java)

        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this.context!!)
        val token = sharedPrefUtil.getToken(this.context!!)
        OrdersFragmentViewModel.setId_Token(id!!, token!!)


        OrdersFragmentViewModel.position.observe(this, Observer {
            (binding.myOrdersRecyclerview.adapter)?.notifyItemChanged(it!!)
        })

        binding.myOrdersRecyclerview.adapter= OrdersRecyclerAdapter(OrdersFragmentViewModel)

        // necessary for DataBinding
        binding.setLifecycleOwner(this)
        binding.viewModel =OrdersFragmentViewModel



        return binding.root
    }


}
