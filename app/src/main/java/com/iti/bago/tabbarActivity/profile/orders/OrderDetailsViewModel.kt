package com.iti.bago.tabbarActivity.profile.orders

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import com.iti.bago.tabbarActivity.ConfirmRetrofitObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//enum class LoadingStatus { LOADING, ERROR, DONE }

class OrderDetailsViewModel : ViewModel() {
    var args: OrderDetailsFragmentArgs? = null
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    val detailResponseObjt = MutableLiveData<DetailsResponseObj>()
    val orderId = MutableLiveData<String>()
    // The external LiveData interface to the property is immutable, so only this class can modify
    var detailResponseObj: DetailsResponseObj? = null

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    lateinit var customer_id: String
    lateinit var customer_token: String

    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
    }

    init {
        detailResponseObjt.value = DetailsResponseObj(
            "", "", "", "", ""
            , "", "", "", "", "", "", "", arrayListOf()
        )
        getResponse()
    }

    fun getResponse() {

        coroutineScope.launch {
            var getStoreSectionsDeferred = ConfirmRetrofitObj.retrofitSercice.getOrderDetails(
                customer_id, args!!.orderId, customer_token
            )
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                detailResponseObjt.value = getStoreSectionsDeferred.await()
                detailResponseObj = detailResponseObjt.value

                _status.value = LoadingStatus.DONE
                Log.i("details", detailResponseObj!!.products[0].product_id)
            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR

                Log.i("error", "${e.message}")
            }

        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
