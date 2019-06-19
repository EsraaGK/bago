package com.iti.bago.tabbarActivity.profile.favourites

import com.squareup.moshi.Json

data class FavouritesObj(
    val id: String,
    val supermarket_id: String,
    val section_id: String,
    val product_id: String,
    val name: String,
    val type: String,
    var position: Int?,
    val price_before: Double,
    val price_after: Double,
    @Json(name = "img_src") val imgSrcUrl: String
)


//
//        "customer_id": 1,
//

//
//
//
//
//        "cart": false