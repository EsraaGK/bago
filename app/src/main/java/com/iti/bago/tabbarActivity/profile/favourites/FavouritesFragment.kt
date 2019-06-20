package com.iti.bago.tabbarActivity.profile.favourites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.FavouritsFragmentBinding
import com.iti.bago.tabbarActivity.TabbarActivity

class FavouritesFragment : Fragment(){



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as TabbarActivity).setFragTitle("Favourites")

        var binding = DataBindingUtil.inflate<FavouritsFragmentBinding>(
            inflater, R.layout.favourits_fragment, container, false
        )


        var favouritesFragmentViewModel = ViewModelProviders.of(this).get(
            FavouritesViewModel::class.java)
        binding.favouritesRecyclerview.adapter =
            FavouritesRecyclerAdapter(favouritesFragmentViewModel)

        // necessary for DataBinding
        binding.setLifecycleOwner(this)
        binding.viewModel =favouritesFragmentViewModel

        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this.context!!)
        val token = sharedPrefUtil.getToken(this.context!!)
        favouritesFragmentViewModel.setId_Token(id!!, token!!)


        favouritesFragmentViewModel.position.observe(this, Observer {
            (binding.favouritesRecyclerview.adapter)?.notifyItemChanged(it!!)

        })

        return binding.root
    }


}
