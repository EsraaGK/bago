package com.iti.bago.tabbarActivity

import com.iti.bago.tabbarActivity.cart.CartPostObj
import com.iti.bago.tabbarActivity.cart.OrderPostObj
import com.iti.bago.tabbarActivity.profile.orders.DetailsResponseObj
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val BASEURL = "http://bago.ibtikar.net.sa/api/"
//2

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
//2+
//GsonConverterFactory.create()
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASEURL)
    .build()

//3
interface ConfirmNetworking {
    @POST("customerorders")
    fun postConfirmOrder(
        @Body orderPostObj: OrderPostObj
        //, @Header("token") token: String
    ): Deferred<CartPostObj>

    @GET("customer/{customer_id}/orders/{order_id}")
    fun getOrderDetails(@Path("customer_id") customer_id:String,
                        @Path("order_id") order_id:String,
                        @Header ("token")token:String): Deferred<DetailsResponseObj>

}

object ConfirmRetrofitObj {
    val retrofitSercice: ConfirmNetworking by lazy {
        com.iti.bago.tabbarActivity.firebase.retrofit.create(ConfirmNetworking::class.java)
    }
}