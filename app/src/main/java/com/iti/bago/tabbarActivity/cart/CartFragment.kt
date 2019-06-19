package com.iti.bago.tabbarActivity.cart

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.CartFragmentBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.iti.bago.tabbarActivity.TabbarActivity


class CartFragment : Fragment() {


    private lateinit var cartFragmentViewModel: CartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = DataBindingUtil.inflate<CartFragmentBinding>(
            inflater, R.layout.cart_fragment, container, false
        )

        val navController = this.findNavController()
        cartFragmentViewModel = ViewModelProviders.of(this).get(CartFragmentViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewModel = cartFragmentViewModel
        binding.cartRecyclerview.adapter = CartRecyclerAdapter(cartFragmentViewModel)

        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this.context!!)
        val token = sharedPrefUtil.getToken(this.context!!)
        cartFragmentViewModel.setId_Token(id!!, token!!)


        cartFragmentViewModel.position.observe(this, Observer {
            (binding.cartRecyclerview.adapter)?.notifyItemChanged(it!!)
            binding.total=cartFragmentViewModel.total_coast.value

            Log.i("observer",binding.total.toString() )
            binding.itemsPrice.text = cartFragmentViewModel.total_coast.value.toString()
        })



        binding.confirmBtn.setOnClickListener {
            navController.navigate(R.id.action_cartFragment_to_confirmOrderFragment)
        }

        (activity as TabbarActivity).setFragTitle("Cart")
        return binding.root
    }
}













