package com.iti.bago.tabbarActivity.cart

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.iti.bago.tabbarActivity.profile.favourites.FavouritePostObj
import com.iti.bago.tabbarActivity.profile.favourites.FavouritesRetrofitObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class LoadingStatus { LOADING, ERROR, DONE, EMPTY }

class CartFragmentViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _cartList = MutableLiveData<List<CartObj>>()
    //myList :List<>
    // The external LiveData interface to the property is immutable, so only this class can modify
    val cartList: LiveData<List<CartObj>>
        get() = _cartList

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
    var total_price: Float = 0.0F
    var total_coast = MutableLiveData<Float>()
    var flag = MutableLiveData<Boolean>()
    init {

        _cartList.value = mutableListOf<CartObj>()

        getResponse()
//        getSupermarketsOrders()

    }


    fun plus(item: CartObj) {

        var positionItem = item.position

        _cartList.value!![positionItem!!].units_no += 1
        _cartList.value!![positionItem!!].item_total_price =
            _cartList.value!![positionItem].price_after * _cartList.value!![positionItem!!].units_no
        calcTotalPrice()
        total_coast.value = total_price
        updateCardItems(item)
        Log.i("xx", "${_cartList.value!![positionItem!!].units_no}")
        position.value = positionItem
    }


    fun minus(item: CartObj) {

        var positionItem = item.position

        if (_cartList.value!![positionItem!!].units_no > 1) {
            _cartList.value!![positionItem].units_no -= 1
            _cartList.value!![positionItem].item_total_price =
                _cartList.value!![positionItem].price_after * _cartList.value!![positionItem].units_no

            Log.i("tot", _cartList.value!![positionItem].item_total_price.toString())
        } else {
            _cartList.value!![positionItem].units_no = 1
        }
        calcTotalPrice()
        total_coast.value = total_price
        updateCardItems(item)
        position.value = positionItem
    }

    fun deletefromList(position: Int) {
        var list = ArrayList<CartObj>()
        list.addAll(_cartList.value!!)

        val deletedObj = list[position]
        list.removeAt(position)
        _cartList.value = list
        if (list.size == 0) {
            _status.value = LoadingStatus.EMPTY
        } else {
            _status.value = LoadingStatus.DONE
        }
        deletefromCard(deletedObj)
    }

    private fun deletefromCard(item: CartObj) {
        var positionItem = item.position
        position.value = positionItem
        var cartPostObj = CartPostObj(item.supermarket_id, item.id, 1, customer_id)

        coroutineScope.launch {
            val posttoCart = CartRetrofitObj.retrofitSercice.deleteCartItems(
                item.id, customer_token
            )
            Log.i("cart_id", item.id)
            try {
                // this will run on a thread managed by Retrofit
                //  val cartPostResponseObj =
                posttoCart.await()
                // Log.i("bb", cartPostResponseObj.units_no.toString())

            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }


        }
    }


    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
    }

    fun getResponse() {

        coroutineScope.launch {
            //getSupermarketsOrders()
            val getCartDeferred = CartRetrofitObj.retrofitSercice.getCartItems(
                customer_id, customer_token
            )
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                _cartList.value = getCartDeferred.await()
                if (_cartList.value != null) {
                    Log.i("bb", "${_cartList.value.toString()} img")

                    if (_cartList.value!!.isEmpty()) {
                        _status.value = LoadingStatus.EMPTY
                    } else {
                        _status.value = LoadingStatus.DONE
                        for (n in _cartList.value!!.indices) {
                            _cartList.value!![n].item_total_price =
                                _cartList.value!![n].price_after * _cartList.value!![n!!].units_no
                        }

                        calcTotalPrice()
                        total_coast.value = total_price
                        position.value = -1
                    }
//                    Log.i("bb", "${_cartList.value!![0].img_src} img")
                }


            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR

                Log.i("error", "${e.message}")
            }

        }

    }

    fun updateCardItems(cartObj: CartObj) {

        coroutineScope.launch {
            val getCartDeferred = CartRetrofitObj.retrofitSercice.updateCartItems(
                cartObj, cartObj.id, customer_token
            )
            try {

                val cartPostObj = getCartDeferred.await()


            } catch (e: Exception) {

                Log.i("error", "${e.message}")
            }

        }

    }

    fun addtoFavourites(v: View, item: CartObj) {
        var positionItem = item.position
        position.value = positionItem
        var favouritePostObj = FavouritePostObj(item.supermarket_id, item.product_id, customer_id)


        Log.i(
            "favourite obj",
            "supermarket_id" + item.supermarket_id + "id" + item.id + "customer_id" +
                    customer_id + "\n" + "customer_token" + customer_token
        )

        coroutineScope.launch {
            val cartPostResponseObj = FavouritesRetrofitObj.retrofitSercice.postFavouriteItems(
                favouritePostObj, customer_token
            )
            try {
                // this will run on a thread managed by Retrofit
                val PostResponseObj = cartPostResponseObj.await()
                if (PostResponseObj.equals(null)) {
                    flag.value =true
                  item.favourite =flag.value!!
                    position.value = -1

                    Toast.makeText(v.context, "Item is already in Favourites !", Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(v.context, "Product is SUCCESSFULLY added to Favourites !", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
                e.message

            }


        }
    }

    fun calcTotalPrice()/*: Float*/ {
        total_price = 0.0F
        var list = ArrayList<CartObj>()
        //   if (_cartList.value!! != null){}

        if (_cartList.value != null) {
            list.addAll(_cartList.value!!)
            for (n in list) {
                Log.i("tot", n.item_total_price.toString())
                total_price += n.item_total_price.toFloat()
            }
        } else {
            total_price = 0.0F
        }
        //  return total_price
    }

    fun confirm(v: View) {
        var list = ArrayList<CartObj>()
        list.addAll(_cartList.value!!)
        var x = list.toTypedArray()
        if (x.size != 0)
            v.findNavController()
                .navigate(CartFragmentDirections.actionCartFragmentToConfirmOrderFragment(total_price, x))
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
