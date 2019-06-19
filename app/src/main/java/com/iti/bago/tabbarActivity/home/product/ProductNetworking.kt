package com.iti.bago.tabbarActivity.home.product

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path


//1
//private  val BASEURL = "https://mars.udacity.com/"
private val BASEURL = "http://bago.ibtikar.net.sa/api/"
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
interface  ProductNetworking{
    /**
     * Returns a Coroutine [Deferred] [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("supermarkets/{supermarket_id}/sections/{section_id}/products")
    fun getProducts(@Path("supermarket_id") supermarket_id:String,
                    @Path("section_id") section_id:String):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<List<ProductObj>>
}
//4
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ProductsRetrofitObj{
 val retrofitSercice:  ProductNetworking by lazy {
        retrofit.create(ProductNetworking::class.java)
    }
}