package com.iti.bago.signup_login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import androidx.navigation.NavController


class LogInFragment_ViewModel_Factory (val navController: NavController) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // is used to pass parameters
        if (modelClass.isAssignableFrom( LogInFragment_ViewModel::class.java)) {
            //  Log.i("VM", "LogInActivity_ViewModel_Factory")
            return  LogInFragment_ViewModel(navController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}