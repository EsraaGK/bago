package com.iti.bago.tabbarActivity.home.storesections

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
//private  val BASEURL ="https://5cefe3255660c400149492b3.mockapi.io/api/"
private val BASEURL="http://bago.ibtikar.net.sa/api/"
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
interface  StoresNetworking{
    /**
     * Returns a Coroutine [Deferred] [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
  //  @GET("realestate")
    //http://bago.ibtikar.net.sa/api/supermarkets/{supermarket_id}/sections
    @GET("supermarkets/{supermarket_id}/sections")
    fun getProducts(@Path("supermarket_id") supermarket_id :String ):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<List<StoreSectionsObj>>
}
//4
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object StoreSectionsRetrofitObj{
    val retrofitSercice:  StoresNetworking by lazy {
        retrofit.create(StoresNetworking::class.java)
    }
}