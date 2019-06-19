package com.iti.bago.tabbarActivity.home.store

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import androidx.navigation.NavController

class HomeFragment_ViewModelFactory  (val navController: NavController) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // is used to pass parameters
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            Log.i("VM","LogInActivity_ViewModel_Factory")
            return HomeFragmentViewModel(navController) as T        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
