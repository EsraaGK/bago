package com.iti.bago.tabbarActivity


import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.DialogTitle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.databinding.ActivityTabbarBinding
import kotlinx.android.synthetic.main.home_fragment.*


class TabbarActivity : AppCompatActivity() {
  lateinit var  toolbar: ActionBar

    lateinit var bottomNavigation: BottomNavigationView
    lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = DataBindingUtil.setContentView<ActivityTabbarBinding>(
            this, R.layout.activity_tabbar
        )

        navController = Navigation.findNavController(this, R.id.tabBarHostFragment)
// var  toolbar: ActionBar =supportActionBar!!
        toolbar = supportActionBar!!
        // toolbar.title = "Home"
        bottomNavigation = binding.navigationView
        setupNavigation()
//
//
//        toolbar = binding.toolbar
//        setSupportActionBar(toolbar)

//        setupActionBarWithNavController(this, navController)
//        NavigationUI.setupWithNavController(bottomNavigation, navController )
        val viewModel = ViewModelProviders.of(this).get(TabbarViewModel::class.java)

        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this)
        val token = sharedPrefUtil.getToken(this)
        viewModel.setId_Token(id!!, token!!)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(ContentValues.TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID customer_token
                val token = task.result?.token
                viewModel.getResponse(token!!)
                Log.i("customer_token fcm", token)
                // Log and toast

            })

    }


    override fun onBackPressed() {

        if (navController.currentDestination!!.id == R.id.homeFragment ||
            navController.currentDestination!!.id == R.id.profileFragment ||
            navController.currentDestination!!.id == R.id.cartFragment ||
            navController.currentDestination!!.id == R.id.settingsFragment
        ) {
            // finishAffinity()
            var startMain = Intent(Intent.ACTION_MAIN)
            startMain.addCategory(Intent.CATEGORY_HOME)
            startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startMain)
        }else {

            super.onBackPressed()
        }
    }

public fun setFragTitle(title: String){
    supportActionBar!!.setTitle(title)
}
    private fun setupNavigation() {
        //    var navController = Navigation.findNavController(this, R.customer_id.tabBarHostFragment)
//        setupActionBarWithNavController(this, navController)
//        NavigationUI.setupWithNavController(bottomNavigation, navController)

        //Your Man Edited Code
        bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.hometabitem -> {
                    toolbar.title = "Home"
                    navController.navigate(R.id.homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.carttabitem -> {
                    toolbar.title = "Cart"
                    navController.navigate(R.id.cartFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settingstabItem -> {
                    toolbar.title = "Settings"
                    navController.navigate(R.id.settingsFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profiletabitem -> {
                    toolbar.title = "Profile"
                    navController.navigate(R.id.profileFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

}


