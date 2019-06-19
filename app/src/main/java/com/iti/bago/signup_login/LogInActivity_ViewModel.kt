package com.iti.bago.signup_login

import android.arch.lifecycle.ViewModel
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import com.iti.bago.R

class LogInActivity_ViewModel(var navController: NavController) : ViewModel() {



    init {

    }

    fun movetoLogin(view: View) {
        var currentFragID = navController.getCurrentDestination()?.getId()
        if (currentFragID == R.id.signUpFragment) {
            navController.navigate(R.id.action_signUpFragment_to_logInFragment)
            view.rootView.findViewById<TextView>(R.id.login_button).setTextColor(
                ContextCompat.getColor(view.context , R.color.pink))
            view.rootView.findViewById<TextView>(R.id.signup_button).setTextColor(
                ContextCompat.getColor(view.context , android.R.color.black))
            Log.i("VM", "action_signUpFragment_to_logInFragment ")
        }

    }

    fun movetoSignup(view: View) {
        var currentFragID = navController.getCurrentDestination()?.getId()
        if (currentFragID == R.id.logInFragment) {
            navController.navigate(R.id.action_logInFragment_to_signUpFragment)
            view.rootView.findViewById<TextView>(R.id.login_button).setTextColor(
                ContextCompat.getColor(view.context ,android.R.color.black))
            view.rootView.findViewById<TextView>(R.id.signup_button).setTextColor(
                ContextCompat.getColor(view.context , R.color.pink))
            Log.i("VM", "action_logInFragment_to_signUpFragment")
        }

    }
}


//Navigation.createNavigateOnClickListener(R.customer_id.signUpFragment, null)