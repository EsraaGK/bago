package com.iti.bago.tabbarActivity.home.store

import com.squareup.moshi.Json
import java.math.BigInteger

data class StoreObj(
    val id: Int,
    var name: String,
    var country_code: String,
    var phone_number: String,
    var address: String,
    var longitude: String,
    var latitude: String,

    // used to map img_src from the JSON to img_src in our class
    @Json(name = "img_src") val imgSrcUrl: String?
)


//"customer_id"=>bigint(20)
//"name"=>varchar(255)
//"country_code"=>varchar(255)
//"phone_number"=>varchar(255)
//"address"=>varchar(255)
//"longitude"=>double
//"latitude"=>double
//"no_of_orders"=>int(11)
//"status"=>enum('Active', 'Inactive')
//"created_at"=>timestamp
//"updated_at"=>timestamp


//(val customer_id: String,
//// used to map img_src from the JSON to img_src in our class
//@Json(name = "img_src") val img_src: String,
//val type: String,
//val price: Double)