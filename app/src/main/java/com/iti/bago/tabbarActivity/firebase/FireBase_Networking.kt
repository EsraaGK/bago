package com.iti.bago.tabbarActivity.firebase

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private val BASEURL = "http://bago.ibtikar.net.sa/api/"
//2

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
//2+
val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASEURL)
    .build()

//3
interface FireBaseNetworking {
    @POST("UserTokenDevice")
    fun postToken(
        @Body fireBase_Obj: FireBase_Obj
    ): Deferred<Void>


}

object FireBaseRetrofitObj {
    val retrofitSercice: FireBaseNetworking by lazy {
 retrofit.create(FireBaseNetworking::class.java)
    }
}