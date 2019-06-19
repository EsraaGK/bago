package com.iti.bago.signup_login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.preference.PreferenceManager
import android.provider.LiveFolders
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.signup_login.onboarding.OnBoardingActivityView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import androidx.navigation.Navigation.findNavController as findNavController1

class LogInFragment_ViewModel(val navController: NavController) : ViewModel() {
    // lateinit var myView : View
    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //validation regex and functions
    val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    )!!

    var userLoginRequest: UserLoginRequest
    lateinit var view: View

    init {
        userLoginRequest = UserLoginRequest("", "")
    }

    fun getResponse() {

        coroutineScope.launch {
            val getUserLoginDeferred = UserRetrofitObj.retrofitSercice.getUserLoggedin(userLoginRequest)
            try {

                // this will run on a thread managed by Retrofit
                UserLoginResponse.userLoginResponseObj = getUserLoginDeferred.await()
                // Log.i("userLoginResponseObj", UserLoginResponse.userLoginResponseObj!!.customer.name)
                val sharedPrefUtil = SharedPrefUtil()
                sharedPrefUtil.saveObj(UserLoginResponse.userLoginResponseObj, view.context)
                var id = sharedPrefUtil.getId(view.context)
              //    navController.navigate(R.id.action_logInFragment_to_onBoardingActivity2)

                PreferenceManager.getDefaultSharedPreferences(view.context).apply {
                    // Check if we need to display our OnboardingFragment
                    if (!getBoolean(OnBoardingActivityView.COMPLETED_ONBOARDING_PREF_NAME, false)) {
                        // The user hasn't seen the OnboardingFragment yet, so show it
                        navController.navigate(R.id.action_logInFragment_to_onBoardingActivity2)
                    } else {
                        navController.navigate(R.id.action_logInFragment_to_tabbarActivity)
                    }

                    Log.i("flag",getBoolean(OnBoardingActivityView.COMPLETED_ONBOARDING_PREF_NAME, false).toString())
                }

            } catch (e: Exception) {

                Toast.makeText(view.context, "Login Faild ,try again", Toast.LENGTH_SHORT).show()
                Log.i("error", "${e.message}")
            }

        }

    }


    fun logIn(v: View) {
        view = v
        var flag = true
        userLoginRequest.email = v.rootView.findViewById<EditText>(R.id.email_login_edittxt).editableText.toString()
        userLoginRequest.password =
            v.rootView.findViewById<EditText>(R.id.passowrd_login_edittxt).editableText.toString()

        if (!validMail(userLoginRequest.email)) {
            flag = false
            v.rootView.findViewById<EditText>(R.id.email_login_edittxt).error = "Invalid Mail"
        }

        if (userLoginRequest.password.isEmpty() || userLoginRequest.password.isBlank()) {
            v.rootView.findViewById<EditText>(R.id.passowrd_login_edittxt).error = "Empty Password"
            flag = false
        }
        if (flag) {
            getResponse()
        }
    }


    fun validMail(input: String): Boolean {
        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input)
        return matcher.find()
    }
}
//log In
//    fun logIn(){
//        Navigation.createNavigateOnClickListener(R.customer_id.action_logInFragment_to_walkthrough_Activity)
////        navController.navigate(R.customer_id.action_signUpFragment4_to_logInFragment)
////        findNavController().navigate(R.customer_id.action_signUpFragment4_to_logInFragment)
//    }