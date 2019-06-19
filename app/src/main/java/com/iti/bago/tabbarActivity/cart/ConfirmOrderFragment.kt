package com.iti.bago.tabbarActivity.cart

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.iti.bago.databinding.ConfirmOrderFragmentBinding

import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.tabbarActivity.TabbarActivity
import kotlinx.android.synthetic.main.confirm_order_fragment.view.*

class ConfirmOrderFragment : Fragment() {

    private lateinit var viewModel: ConfirmorderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as TabbarActivity).setFragTitle("Confirm Order")
        val binding = DataBindingUtil.inflate<ConfirmOrderFragmentBinding>(
            inflater, R.layout.confirm_order_fragment, container, false
        )

        val navController = this.findNavController()


        val x = ConfirmOrderFragmentArgs.fromBundle(arguments!!).orderList
        val viewModelFactory = ConfirmOrder_VMFactory(x)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ConfirmorderViewModel::class.java)
        Log.i("arrayfrom confirm frag", x[0].name)
          viewModel.args = ConfirmOrderFragmentArgs.fromBundle(arguments!!)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this.context!!)
        val token = sharedPrefUtil.getToken(this.context!!)
        viewModel.setId_Token(id!!, token!!)

        return binding.root
    }

}
