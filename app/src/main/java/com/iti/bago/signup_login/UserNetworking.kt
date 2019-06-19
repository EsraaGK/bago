package com.iti.bago.signup_login

import com.iti.bago.signup_login.onboarding.SignupResponseObj
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private val BASEURL ="http://bago.ibtikar.net.sa/api/customer/"
//private val BASEURL ="https://5cefe3255660c400149492b3.mockapi.io/api/"
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
interface  UserNetworking{
    /**
     * Returns a Coroutine [Deferred] [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    // @GET("realestate")
    @POST("register")
    fun getUserRegistered(@Body userRequest: UserSignupRequest):
    // The Coroutine Call Adapter allows us
    //to return a Deferred, a Job with a result
            Deferred<SignupResponseObj>
    //___________________________________________________________________________
    @POST("login")
    fun getUserLoggedin(@Body userRequest: UserLoginRequest):
            Deferred<UserLoginResponseObj>

}
//4
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object UserRetrofitObj{
    val retrofitSercice:  UserNetworking by lazy {
        retrofit.create(UserNetworking::class.java)
    }
}