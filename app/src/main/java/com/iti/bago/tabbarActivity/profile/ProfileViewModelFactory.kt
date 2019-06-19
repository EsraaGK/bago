package com.iti.bago.tabbarActivity.profile

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import androidx.navigation.NavController
import com.iti.bago.signup_login.LogInActivity_ViewModel


class ProfileViewModelFactory (val navController: NavController) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // is used to pass parameters
        if (modelClass.isAssignableFrom(ProfileFragmentViewModel::class.java)) {

            return ProfileFragmentViewModel(navController) as T        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}