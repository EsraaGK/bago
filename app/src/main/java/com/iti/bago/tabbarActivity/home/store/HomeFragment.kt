package com.iti.bago.tabbarActivity.home.store

import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues.TAG
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.HomeFragmentBinding
import com.iti.bago.signup_login.UserLoginResponse
import com.iti.bago.tabbarActivity.TabbarActivity
import com.iti.bago.tabbarActivity.home.storesections.StoreSectionsFragmentArgs

class HomeFragment : Fragment() {


    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater, R.layout.home_fragment, container, false
        )
        val navController = this.findNavController()
        var homeFragmentViewModelFactory = HomeFragment_ViewModelFactory(navController)

        homeFragmentViewModel = ViewModelProviders.of(
            this, homeFragmentViewModelFactory
        ).get(HomeFragmentViewModel::class.java)
        // TODO: Use the ViewModel

//shared preferences
        val sharedPrefUtil = SharedPrefUtil()
        val chooseNavigation = sharedPrefUtil.saveNewUserFlag("false", this.context!!)
        val flag = sharedPrefUtil.getNewUserFlag(this.context!!)
        if (chooseNavigation) Log.i("shared pref", chooseNavigation.toString())
       val id = sharedPrefUtil.getId(this.context!!)
        UserLoginResponse.userLoginResponseObj = sharedPrefUtil.getObj(this.context!!)
        //set the recycler view
        binding.setLifecycleOwner(this)
        binding.viewModel = homeFragmentViewModel
        binding.storesRecyclerview.adapter =
            StoresRecyclerAdapter(homeFragmentViewModel)

        (activity as TabbarActivity ).setFragTitle("Home")

        return binding.root
    }


}
