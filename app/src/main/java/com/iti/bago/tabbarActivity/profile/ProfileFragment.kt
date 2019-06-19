package com.iti.bago.tabbarActivity.profile

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.iti.bago.R
import com.iti.bago.databinding.ProfileFragmentBinding
import com.iti.bago.tabbarActivity.TabbarActivity

class ProfileFragment : Fragment() {


   lateinit var binding: ProfileFragmentBinding
    private lateinit var profileFragmentViewModel: ProfileFragmentViewModel
    lateinit var profileFragmentViewModelFactory: ProfileViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as TabbarActivity).setFragTitle("Profile")
        binding = DataBindingUtil.inflate<ProfileFragmentBinding>(
            inflater, R.layout.profile_fragment, container, false
        )
        var navController= this.findNavController()

        profileFragmentViewModelFactory = ProfileViewModelFactory(navController)
        profileFragmentViewModel = ViewModelProviders.of(this,
            profileFragmentViewModelFactory ).get(ProfileFragmentViewModel::class.java)

        // necessary for DataBinding
        binding.setLifecycleOwner(this)
        binding.viewModel =profileFragmentViewModel



        return binding.root

    }
}
