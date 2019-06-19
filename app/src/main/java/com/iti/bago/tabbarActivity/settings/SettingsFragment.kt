package com.iti.bago.tabbarActivity.settings

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.SettingsFragmentBinding
import com.iti.bago.signup_login.LogInActivity
import com.iti.bago.signup_login.UserData
import com.iti.bago.signup_login.UserLoginResponseObj
import com.iti.bago.signup_login.onboarding.OnBoardingActivityView
import com.iti.bago.tabbarActivity.TabbarActivity
import kotlinx.android.synthetic.main.settings_fragment.*
import com.iti.bago.tabbarActivity.settings.SettingsFragment as TabbarActivitySettingsFragment

class SettingsFragment : Fragment() {

    companion object {
        var LOGGED_OUT: String = "log_out"
    }

    lateinit var navController: NavController
    private lateinit var settingsFragmentViewModel: SettingsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = DataBindingUtil.inflate<SettingsFragmentBinding>(
            inflater, R.layout.settings_fragment, container, false
        )
        navController = this.findNavController()
        var SettingsFragmentFactory = SettingsFragmentFactory(navController)
        settingsFragmentViewModel = ViewModelProviders.of(
            this,
            SettingsFragmentFactory
        ).get(SettingsFragmentViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.viewModel = settingsFragmentViewModel

        binding.logout.setOnClickListener {
            val sharedPrefUtil = SharedPrefUtil()
            val id = sharedPrefUtil.saveObj(
                UserLoginResponseObj(
                    UserData(
                        "",
                        "", "", "",
                        "", "", "", "", "", "", ""
                    ), ""
                ), activity!!
            )

            var intent = Intent(activity!!, LogInActivity::class.java)
            intent.putExtra("logged_out",true)
            startActivity(intent)
            activity!!.finish()
        }
        (activity as TabbarActivity).setFragTitle("Settings")
        return binding.root
    }

}
