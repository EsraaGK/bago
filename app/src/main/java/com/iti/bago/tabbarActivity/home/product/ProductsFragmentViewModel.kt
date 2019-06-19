package com.iti.bago.tabbarActivity.home.product

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import android.view.View
import android.widget.Toast
import com.iti.bago.tabbarActivity.cart.CartPostObj
import com.iti.bago.tabbarActivity.cart.CartRetrofitObj
import com.iti.bago.tabbarActivity.profile.favourites.FavouritePostObj
import com.iti.bago.tabbarActivity.profile.favourites.FavouritesRetrofitObj
import kotlinx.coroutines.*


enum class LoadingStatus { LOADING, ERROR, DONE }


class ProductsFragmentViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _productsList = MutableLiveData<List<ProductObj>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val productsList: LiveData<List<ProductObj>>
        get() = _productsList


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var args: ProductsFragmentArgs? = null
    var position = MutableLiveData<Int>()

    lateinit var customer_id: String
    lateinit var customer_token: String


    init {
        getResponse()
    }

    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
        Log.i("post", customer_id)
        Log.i("post ", customer_token)
    }


    fun getResponse() {

        coroutineScope.launch {
            var getProductsDeferred = ProductsRetrofitObj.retrofitSercice.getProducts(
                args!!.storeId, args!!.sectionId
            )
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                _productsList.value = getProductsDeferred.await()
                _status.value = LoadingStatus.DONE
//                Log.i("n", response.body()!!.string() )
            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR
                _productsList.value = ArrayList()
                Log.i("n", "${e.message}")
            }

        }


    }


    fun addtoCard(v: View, item: ProductObj) {
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

    fun addtoFavourites(v: View, item: ProductObj) {
        var positionItem = item.position
        position.value = positionItem
        var favouritePostObj = FavouritePostObj(item.supermarket_id, item.id,customer_id )

        Log.i("favourite obj",""+item.supermarket_id+""+item.id+""+customer_id+"\n"+customer_token)

        coroutineScope.launch {
            val cartPostResponseObj = FavouritesRetrofitObj.retrofitSercice.postFavouriteItems(
                favouritePostObj, customer_token)
            try {
                // this will run on a thread managed by Retrofit
                val PostResponseObj = cartPostResponseObj.await()
                Toast.makeText(v.context, "Product is SUCCESSFULLY added to Favourites !", Toast.LENGTH_SHORT).show()
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
