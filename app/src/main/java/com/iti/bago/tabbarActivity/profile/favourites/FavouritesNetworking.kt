package com.iti.bago.tabbarActivity.profile.favourites


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
interface  FavouritesNetworking{

    @GET("customers/{customer_id}/favourite")
    fun getFavouritesItems(
        @Path("customer_id") customer_id: String
       /* , @Header("token") token: String
    */): Deferred<List<FavouritesObj>>

    @POST("favourite")
    fun postFavouriteItems(
        @Body favouritePostObj: FavouritePostObj,
        @Header("token") token: String
    ): Deferred<FavouritePostObj>

    @DELETE("favourite/{favourite_id}")
    fun deleteFavouritesItems(
        @Path("favourite_id") favourite_id : String,
        @Header("token") token: String
    ): Deferred<Void>

}
//4
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object FavouritesRetrofitObj{
    val retrofitSercice: FavouritesNetworking by lazy {
        retrofit.create(FavouritesNetworking::class.java)
    }
}
