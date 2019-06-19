package com.iti.bago.signup_login

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.ActivityLoginBinding
import com.iti.bago.signup_login.onboarding.OnBoardingActivityView
import com.iti.bago.signup_login.onboarding.OnBoardingActivityView.Companion.COMPLETED_ONBOARDING_PREF_NAME
import com.iti.bago.tabbarActivity.settings.SettingsFragment.Companion.LOGGED_OUT
import android.arch.lifecycle.ViewModelProvider as LifecycleViewModelProvider


class LogInActivity : AppCompatActivity() {


    lateinit var binding: ActivityLoginBinding
    lateinit var logInActivity_ViewModel: LogInActivity_ViewModel
    lateinit var logInActivity_ViewModel_Factory: LogInActivity_ViewModel_Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.myStyle)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this, R.layout.activity_login
        )


        val navController = this.findNavController(R.id.myNavHostFragment)

        //Passing The navController is necessary to move
        // between Fragments using buttons in the activity
        logInActivity_ViewModel_Factory =
            LogInActivity_ViewModel_Factory(navController)

        logInActivity_ViewModel = ViewModelProviders.of(
            this,
            logInActivity_ViewModel_Factory
        ).get(LogInActivity_ViewModel::class.java)


        // necessary for DataBinding
        binding.setLifecycleOwner(this)
        binding.loginActivityVM = logInActivity_ViewModel
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        // finish();
    }

}

// var  currentFragID = navController.getCurrentDestination()?.getCustomer_id( )
//   NavigationUI.setupActionBarWithNavController(this, navController)


////             it?.findNavController()?.navigate(R.customer_id.action_signUpFragment4_to_logInFragment)
//            Navigation.createNavigateOnClickListener(R.customer_id.action_signUpFragment4_to_logInFragment)


//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.customer_id.myNavHostFragment)
//        return navController.navigateUp()
//
//
//    }





