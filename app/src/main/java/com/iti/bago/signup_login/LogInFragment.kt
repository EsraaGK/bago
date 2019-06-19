package com.iti.bago.signup_login


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.iti.bago.R
import com.iti.bago.databinding.FragmentLogInBinding
import com.iti.bago.signup_login.onboarding.OnBoardingActivity
import com.iti.bago.signup_login.onboarding.OnBoardingActivityView


class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
    lateinit var logInFragment_ViewModel_Factory: LogInFragment_ViewModel_Factory
    lateinit var logInFragment_ViewModel: LogInFragment_ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLogInBinding>(
            inflater, R.layout.fragment_log_in, container, false
        )
        //  val navController = activity!!.findNavController(R.customer_id.myNavHostFragment)
        val navController = this.findNavController()
        logInFragment_ViewModel_Factory =
            LogInFragment_ViewModel_Factory(navController)

        logInFragment_ViewModel = ViewModelProviders.of(this, logInFragment_ViewModel_Factory).get(
            LogInFragment_ViewModel::class.java
        )
        binding.setLifecycleOwner(this)
        binding.loginFragVM = logInFragment_ViewModel

//        logInFragment_ViewModel.loginFlag!!.observe(this, Observer {
//            val intent = Intent(activity!!,OnBoardingActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            context!!.startActivity(intent)
//        })

        return binding.root
    }


}
