package com.iti.bago.signup_login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import androidx.navigation.NavController

class LogInActivity_ViewModel_Factory (val navController: NavController ) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // is used to pass parameters
        if (modelClass.isAssignableFrom(LogInActivity_ViewModel::class.java)) {
            Log.i("VM","LogInActivity_ViewModel_Factory")
            return LogInActivity_ViewModel(navController) as T        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
