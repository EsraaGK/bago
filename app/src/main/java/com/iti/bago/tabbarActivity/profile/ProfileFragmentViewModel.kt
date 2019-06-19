package com.iti.bago.tabbarActivity.profile

import android.arch.lifecycle.ViewModel;
import androidx.navigation.NavController
import com.iti.bago.R

class ProfileFragmentViewModel(var navController : NavController) : ViewModel() {
 fun   movetoUserInformation(){}
    fun   movetoMyorders (){
        navController.navigate(R.id.action_profileFragment_to_ordersFragment)
    }
    fun   movetoFavouritList (){
        navController.navigate(R.id.action_profileFragment_to_favourits)
    }
    fun   movetoTrackorder (){}
    fun   movetoNotification (){}
}
