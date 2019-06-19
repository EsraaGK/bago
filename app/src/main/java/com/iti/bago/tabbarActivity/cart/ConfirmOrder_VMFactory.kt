package com.iti.bago.tabbarActivity.cart

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import androidx.navigation.NavController

class ConfirmOrder_VMFactory (val tempArray :Array<CartObj>) : ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            // is used to pass parameters
            if (modelClass.isAssignableFrom(ConfirmorderViewModel::class.java)) {
                Log.i("VM","LogInActivity_ViewModel_Factory")
                return ConfirmorderViewModel(tempArray) as T        }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }