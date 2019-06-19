package com.iti.bago.tabbarActivity.settings

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import androidx.navigation.NavController

class SettingsFragmentFactory (val navController: NavController) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // is used to pass parameters
        if (modelClass.isAssignableFrom(SettingsFragmentViewModel::class.java)) {

            return SettingsFragmentViewModel(navController) as T        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}