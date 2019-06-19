package com.iti.bago.tabbarActivity.home.storesections

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.iti.bago.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class LoadingStatus { LOADING, ERROR, DONE }


class StoreSectionsViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _storesList = MutableLiveData<List<StoreSectionsObj>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val storesList: LiveData<List<StoreSectionsObj>>
        get() = _storesList


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getResponse() on init so we can display status immediately.
     */
    var args: StoreSectionsFragmentArgs? = null
    lateinit var section_id: String

    init {
        getResponse()

    }

    fun getSectionId(id: String) {
        section_id = id
    }

    fun movetoProducts(v: View, storeSectionsObj: StoreSectionsObj) {
        var section_id = storeSectionsObj.id
        var store_id = args!!.id
        // v!!.findNavController().navigate(R.customer_id.action_storeSections_to_productsFragment)
        v!!.findNavController()
            .navigate(StoreSectionsFragmentDirections.actionStoreSectionsToProductsFragment(section_id, store_id))

        Log.i("View", "View")
    }

    fun getResponse() {

        coroutineScope.launch {
            var getStoreSectionsDeferred = StoreSectionsRetrofitObj.retrofitSercice.getProducts(args!!.id)
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                _storesList.value = getStoreSectionsDeferred.await()
                _status.value = LoadingStatus.DONE
                if (args != null) Log.i("moved", args!!.id)
            } catch (e: Exception) {
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
