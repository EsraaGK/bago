package com.iti.bago.tabbarActivity.profile.favourites

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import android.view.View
import android.widget.Toast
import com.iti.bago.tabbarActivity.cart.CartPostObj
import com.iti.bago.tabbarActivity.cart.CartRetrofitObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class LoadingStatus { LOADING, ERROR, DONE, EMPTY }

class FavouritesViewModel() : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _favouritesList = MutableLiveData<List<FavouritesObj>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val favouritesList: LiveData<List<FavouritesObj>>
        get() = _favouritesList

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getResponse() on init so we can display status immediately.
     */

    var position = MutableLiveData<Int>()

    var listSize = 0
    lateinit var customer_id: String
    lateinit var customer_token: String

    init {

        _favouritesList.value = mutableListOf<FavouritesObj>()

        getResponse()

    }

    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
    }

    fun getResponse() {

        coroutineScope.launch {
            val getFavouritesDeferred = FavouritesRetrofitObj.retrofitSercice.getFavouritesItems(
                customer_id/*, customer_token*/
            )
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                _favouritesList.value = getFavouritesDeferred.await()
                if (_favouritesList.value.toString() == "[]") {
                    _status.value = LoadingStatus.EMPTY

                } else {
                    _status.value = LoadingStatus.DONE
                }
                Log.i("bb", _favouritesList.value.toString())

            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR

                Log.i("error", "${e.message}")
            }

        }


    }

    fun deletefromList(position: Int) {
        var list = ArrayList<FavouritesObj>()
        list.addAll(_favouritesList.value!!)
        val deletedObj = list[position]
        list.removeAt(position)
        _favouritesList.value = list
        if (list.size == 0) {
            _status.value = LoadingStatus.EMPTY
        } else {
            _status.value = LoadingStatus.DONE
        }
        deletefromFavourites(deletedObj)
    }

    private fun deletefromFavourites(item: FavouritesObj) {
        var positionItem = item.position
        position.value = positionItem
        var cartPostObj = FavouritePostObj(item.supermarket_id, item.id, customer_id)

        coroutineScope.launch {
            val FavouritesDefered = FavouritesRetrofitObj.retrofitSercice.deleteFavouritesItems(
                item.id, customer_token
            )
            Log.i("favourites_id", item.id)
            try {

                FavouritesDefered.await()

            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }


        }
    }

    fun addtoCard(v: View, item: FavouritesObj) {
        var positionItem = item.position
        position.value = positionItem
        var cartPostObj = CartPostObj(item.supermarket_id, item.id, 1, customer_id)

        Log.i("cart obj",""+item.supermarket_id+""+item.id+""+customer_id+"\n"+customer_token)

        coroutineScope.launch {
            val posttoCart = CartRetrofitObj.retrofitSercice.postCartItems(
                cartPostObj, customer_token)
            try {
                // this will run on a thread managed by Retrofit
                val cartPostResponseObj = posttoCart.await()
                Log.i("bb", cartPostResponseObj.units_no.toString())
                Toast.makeText(v.context, "Product is SUCCESSFULLY added to cart !", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
                e.message

            }


        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
