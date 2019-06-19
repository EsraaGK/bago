package com.iti.bago.tabbarActivity.cart

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

//private val BASEURL = "https://mars.udacity.com/"
//private val BASEURL ="https://5cefe3255660c400149492b3.mockapi.io/api/"
private val BASEURL = "http://bago.ibtikar.net.sa/api/"
//2

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
//2+
val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASEURL)
    .build()

//3
interface CartNetworking {
    /**
     * Returns a Coroutine [Deferred] [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    // @GET("realestate")
    @GET("customers/{customer_id}/cart")
    fun getCartItems(
        @Path("customer_id") customer_id: String
        , @Header("token") token: String
    ): Deferred<List<CartObj>>

    @POST("cart")
    fun postCartItems(
        @Body cartPostObj: CartPostObj,
        @Header("token") token: String
    ): Deferred<CartPostObj>

    @DELETE("cart/{cart_id}")
    fun deleteCartItems(
        @Path("cart_id") cart_id : String,
        @Header("token") token: String
    ): Deferred<Void>

    @PUT("cart/{cart_id}")
    fun updateCartItems(@Body cartObj: CartObj,
                        @Path("cart_id") cart_id : String,
                        @Header("token") token: String
    ): Deferred<CartPostObj>

    @POST("customerorders")
    fun postConfirmOrder(
        @Body orderPostObj: OrderPostObj
        //, @Header("token") token: String
    ): Deferred<CartPostObj>
}
//4
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CartRetrofitObj {
    val retrofitSercice: CartNetworking by lazy {
        com.iti.bago.tabbarActivity.firebase.retrofit.create(CartNetworking::class.java)
    }
}