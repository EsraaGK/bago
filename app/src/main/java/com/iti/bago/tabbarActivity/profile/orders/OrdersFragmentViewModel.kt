package com.iti.bago.tabbarActivity.profile.orders

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class LoadingStatus { LOADING, ERROR, DONE, EMPTY }

class OrdersFragmentViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadingStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<LoadingStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _ordersList = MutableLiveData<List<OrderResponseObj>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val ordersList: LiveData<List<OrderResponseObj>>
        get() = _ordersList

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getResponse() on init so we can display status immediately.
     */


    var position = MutableLiveData<Int>()

    lateinit var customer_id: String
    lateinit var customer_token: String


    init {

        _ordersList.value = mutableListOf<OrderResponseObj>()

        getResponse()

    }


    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
    }
//
//    fun deletefromList(position: Int) {
//        var list = ArrayList<OrderDetailsObj>()
//        list.addAll(_ordersList.value!!)
//        val deletedObj = list[position]
//        list.removeAt(position)
//        _ordersList.value = list
//        if (list.size == 0) {
//            _status.value = LoadingStatus.EMPTY
//        } else {
//            _status.value = LoadingStatus.DONE
//        }
//        deletefromCard(deletedObj)
//    }
//
//    private fun deletefromCard(item: OrderDetailsObj) {
//        var positionItem = item.position
//        position.value = positionItem
//        var cartPostObj = CartPostObj(item.supermarket_id, item.id, 1, customer_id)
//
//        coroutineScope.launch {
//            val posttoCart = OrdersRetrofitObj.retrofitSercice.deleteOrderItems(
//                item.id, customer_token
//            )
//            Log.i("cart_id", item.id)
//            try {
//                // this will run on a thread managed by Retrofit
//                //  val cartPostResponseObj =
//                posttoCart.await()
//                // Log.i("bb", cartPostResponseObj.units_no.toString())
//
//            } catch (e: Exception) {
//                Log.i("error", "${e.message}")
//            }
//
//
//        }
//    }


    fun getResponse() {

        coroutineScope.launch {
            val getOrderDeferred = OrdersRetrofitObj.retrofitSercice.getOrdersItems(
                customer_id, customer_token)
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                _ordersList.value  = getOrderDeferred.await()
                if (_ordersList.value.toString() == "[]" ){
                    _status.value = LoadingStatus.EMPTY

                }
                else {
                    _status.value = LoadingStatus.DONE
                }
                Log.i("bb", _ordersList.value.toString())

            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR

                Log.i("error", "${e.message}")
            }

        }


    }

 fun showOrder(v: View , item :OrderResponseObj){
     v.findNavController().navigate(OrdersFragmentDirections.actionOrdersFragmentToOrderDetailsFragment(item.order_id))
 }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
