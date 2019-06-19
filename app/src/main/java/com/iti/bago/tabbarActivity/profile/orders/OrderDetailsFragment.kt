package com.iti.bago.tabbarActivity.profile.orders

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.OrderdetailsFragmentBinding
import com.iti.bago.tabbarActivity.TabbarActivity

class OrderDetailsFragment : Fragment() {

    private lateinit var viewModel: OrderDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as TabbarActivity).setFragTitle("Order Details")
        val binding = DataBindingUtil.inflate<OrderdetailsFragmentBinding>(
            inflater, R.layout.orderdetails_fragment, container, false
        )
        viewModel = ViewModelProviders.of(this).get(OrderDetailsViewModel::class.java)
        val args = OrderDetailsFragmentArgs.fromBundle(arguments!!)
        viewModel.args = args

        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this.context!!)
        val token = sharedPrefUtil.getToken(this.context!!)
        viewModel.setId_Token(id!!, token!!)
        binding.productOrderListview.adapter = DetailsOrderAdapter()
        viewModel.detailResponseObjt.observe(this, Observer {
            binding.orderDetails = it
            if (viewModel.detailResponseObj != null) {
                Log.i("details detail", viewModel.detailResponseObj!!.order_id)


                (binding.productOrderListview.adapter)?.notifyDataSetChanged()
            }
        })


        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }


}
