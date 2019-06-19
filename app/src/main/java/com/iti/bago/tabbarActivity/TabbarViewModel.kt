package com.iti.bago.tabbarActivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.iti.bago.tabbarActivity.firebase.FireBaseRetrofitObj
import com.iti.bago.tabbarActivity.firebase.FireBase_Obj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TabbarViewModel():ViewModel() {
  //  private val _fireBase_Obj = MutableLiveData<FireBase_Obj>()

    // The external LiveData interface to the property is immutable, so only this class can modify



    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    lateinit var customer_id: String
    lateinit var customer_token: String



    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
        Log.i("post", customer_id)
        Log.i("post ", customer_token)
    }


    fun getResponse(firebaseToken:String) {
        val _fireBase_Obj= FireBase_Obj(customer_id ,firebaseToken )

        coroutineScope.launch {
            var getProductsDeferred = FireBaseRetrofitObj.retrofitSercice.postToken(_fireBase_Obj)
            try {

               getProductsDeferred.await()
                Log.i("firebase", _fireBase_Obj.token )
            } catch (e: Exception) {

                Log.i("n", "${e.message}")
            }

        }


    }


}