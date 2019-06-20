package com.iti.bago.signup_login



import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
lateinit var signUpFragment_ViewModel_Factory : SignUpFragment_ViewModel_Factory
    lateinit var signUpFragment_ViewModel : SignUpFragment_ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater , R.layout.fragment_sign_up, container, false)

      //  val navController = activity!!.findNavController(R.customer_id.myNavHostFragment)
        val navController = this.findNavController()

        signUpFragment_ViewModel_Factory =
            SignUpFragment_ViewModel_Factory(navController)
        signUpFragment_ViewModel = ViewModelProviders.of(this , signUpFragment_ViewModel_Factory).get(
            SignUpFragment_ViewModel::class.java)

        // necessary for DataBinding
        binding.setLifecycleOwner(this)
        binding.signupFragmentVM = signUpFragment_ViewModel


        var sharedPrefUtilil = SharedPrefUtil()
        if(!(sharedPrefUtilil.getId(activity!!)== null||sharedPrefUtilil.getId(activity!!)== "")){
            navController.navigate(R.id.action_signUpFragment_to_tabbarActivity)
        }

        return binding.root
    }

//    // Hide the keyboard.
//    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//    imm.hideSoftInputFromWindow(view.windowToken, 0)

}
