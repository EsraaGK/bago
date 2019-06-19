package com.iti.bago.tabbarActivity.home.storesections

import com.squareup.moshi.Json

data class StoreSectionsObj(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val name: String
)


////
//val customer_id :String
//val  name :String
//val img_url :String


//[
//{
//    "customer_id": "11",
//    "img_src": "https://assets3.thrillist.com/v1/image/1694700/size/tmg-article_default_mobile.jpg",
//    "name": "milk"
//}
//]

//val customer_id: String,
//// used to map img_src from the JSON to img_src in our class
//@Json(name = "img_src") val img_src: String,
//val type: String,
//val price: Double