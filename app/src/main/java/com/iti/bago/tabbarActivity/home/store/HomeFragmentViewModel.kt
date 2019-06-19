package com.iti.bago.tabbarActivity.home.store

import android.arch.lifecycle.LiveData

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import androidx.navigation.NavController
import com.iti.bago.tabbarActivity.home.storesections.StoreSectionsFragmentDirections
import com.iti.bago.tabbarActivity.home.storesections.StoreSectionsRetrofitObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class LoadingStatus { LOADING, ERROR, DONE }


class HomeFragmentViewModel(val navController: NavController) : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _storesList = MutableLiveData<List<StoreObj>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val storesList: LiveData<List<StoreObj>>
        get() = _storesList


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getResponse() on init so we can display status immediately.
     */

    init {
        getResponse()
    }


    fun movetoSection(storeObj:StoreObj){
        var section_id = storeObj.id.toString()
        Log.i("viewmodel", section_id)

         navController.navigate(HomeFragmentDirections.actionHomeFragmentToStoreSections(section_id))
    }



    fun getResponse(){

        coroutineScope.launch {
            var getStoresDeferred = StoresRetrofitObj.retrofitSercice.getStores()
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                _storesList.value = getStoresDeferred.await()
                _status.value = LoadingStatus.DONE
                Log.i("n", _storesList.value.toString())
            }catch (e:Exception){
                _status.value = LoadingStatus.ERROR
                _storesList.value = ArrayList()
                Log.i("n", "${e.message}")
            }

        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
