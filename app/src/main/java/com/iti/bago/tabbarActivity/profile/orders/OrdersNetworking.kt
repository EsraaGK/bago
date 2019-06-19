package com.iti.bago.tabbarActivity.profile.orders



import com.iti.bago.tabbarActivity.cart.CartPostObj
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

//private val BASEURL = "https://mars.udacity.com/"
private val BASEURL ="http://bago.ibtikar.net.sa/api/"
//2

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
//2+
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASEURL)
    .build()
//3
interface  OrdersNetworking{
//http://bago.ibtikar.net.sa/api/customer/{cutomer_id}/ordersproducts
    @GET("customer/{customer_id}/ordersproducts")
    fun getOrdersItems(
        @Path("customer_id") customer_id: String
        , @Header("token") token: String

    ): Deferred<List<OrderResponseObj>>

//    @POST("cart")
//    fun postOrderItems(
//        @Body cartPostObj: CartPostObj,
//        @Header("token") token: String
//    ): Deferred<CartPostObj>

//    @DELETE("cart/{cart_id}")
//    fun deleteOrderItems(
//        @Path("cart_id") cart_id : String,
//        @Header("token") token: String
//    ): Deferred<Void>



}
//4
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object OrdersRetrofitObj{
    val retrofitSercice: OrdersNetworking by lazy {
        retrofit.create(OrdersNetworking::class.java)
    }
}


