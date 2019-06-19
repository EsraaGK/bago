package com.iti.bago.tabbarActivity.cart

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartObj(
    val id: String,
    val supermarket_id: String,
    val section_id: String,
    val product_id:String,
    var favourite: Boolean,
    val name: String,
    val type: String,
    var units_no: Int,
    var position: Int?,
    val price_before: Double,
    val price_after: Double,
    var item_total_price: Double = price_after *units_no,
//    val img_src: String
 var img_src: String?
// @Json(name = "img_src") var img_src: String?
): Parcelable{

}


//val customer_id: String,
//// used to map img_src from the JSON to img_src in our class
//@Json(name = "img_src") val img_src: String,
//val type: String,
//val price: Double
//
//val customer_id :String,
//val name :String,
//val type:String,
//val price_before :Double,
//val price_after	:Double,
//val img_src :String,
//val supermarket_id	:String,
//val section_id	:String,
//val type :String ,
// val size :Int,
