package com.iti.bago.tabbarActivity.settings

import android.arch.lifecycle.ViewModel;
import androidx.navigation.NavController
import com.iti.bago.R

class SettingsFragmentViewModel(val navController: NavController) : ViewModel() {

    fun movetoAbout(){
        navController.navigate(R.id.action_settingsFragment_to_webViewFragment)
    }
    fun movetoFAQSA(){
        navController.navigate(R.id.action_settingsFragment_to_FAQSA)
    }

    fun movetoContactus(){
        navController.navigate(R.id.action_settingsFragment_to_contactFragment)
    }

    fun movetoPrivacypolicy(){
        navController.navigate(R.id.action_settingsFragment_to_privacyPolicyFragment)
    }
}
