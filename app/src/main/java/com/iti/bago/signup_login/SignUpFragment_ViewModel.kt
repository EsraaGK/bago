package com.iti.bago.signup_login

import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import com.iti.bago.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpFragment_ViewModel(val navController: NavController) : ViewModel() {
    lateinit var userSignupRequest: UserSignupRequest
    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //validation regex and functions
    val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    )!!

    lateinit var v: View

    init {
        userSignupRequest = UserSignupRequest("", "", "", "")
    }

    fun movetoLogin(v: View) {
        this.v=v
        var flag = true
        userSignupRequest.password =
            v.rootView.findViewById<EditText>(R.id.firstpassowrd_edittxt).editableText.toString()
        userSignupRequest.password_confirmation =
            v.rootView.findViewById<EditText>(R.id.secondpassword_edittxt).editableText.toString()
        userSignupRequest.name = v.rootView.findViewById<EditText>(R.id.name).editableText.toString()
        userSignupRequest.email = v.rootView.findViewById<EditText>(R.id.email_edittxt).editableText.toString()

        if (userSignupRequest.name.isEmpty() || userSignupRequest.name.isBlank()) {
            flag = false
            v.rootView.findViewById<EditText>(R.id.name).error = "Empty Name"
        }
        if (!validMail(userSignupRequest.email)) {
            flag = false
            v.rootView.findViewById<EditText>(R.id.email_edittxt).error = "Invalid Mail"
        }

        if (userSignupRequest.password.isEmpty() || userSignupRequest.password.isBlank()) {
            v.rootView.findViewById<EditText>(R.id.firstpassowrd_edittxt).error = "Empty Password"
            flag = false
        }

        if (userSignupRequest.password_confirmation.isEmpty() || userSignupRequest.password_confirmation.isBlank()) {
            v.rootView.findViewById<EditText>(R.id.secondpassword_edittxt).error = "Empty Repassword"
            flag = false
        }

        if (!(userSignupRequest.password == userSignupRequest.password_confirmation)) {
            v.rootView.findViewById<EditText>(R.id.secondpassword_edittxt).error = "Password Doesn't Match"
            flag = false
        }

        if (flag) {
            getResponse()
        }

    }


    fun getResponse() {
        //   var successFlag = false
        coroutineScope.launch {
            val getUserDeferred = UserRetrofitObj.retrofitSercice.getUserRegistered(userSignupRequest)
            try {

                // this will run on a thread managed by Retrofit
                //  var reg_token =
                var signupResponseObj = getUserDeferred.await()
                Log.i("userLoginResponseObj", signupResponseObj.customer.email)
                //  successFlag = true
                navController.navigate(R.id.action_signUpFragment_to_logInFragment)
            } catch (e: Exception) {
                Toast.makeText(v.context, "Sign Up Faild ,try again", Toast.LENGTH_SHORT).show()

                Log.i("error", "${e.message}")
            }
        }
        //  return successFlag
    }

    fun validMail(input: String): Boolean {
        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input)
        return matcher.find()
    }
}
//
//object UserResponse{
//    val userLoginResponseObj:  UserLoginObj lazy {
//       set()=
//    }
//}