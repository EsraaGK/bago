package com.iti.bago.tabbarActivity.profile.orders

import com.squareup.moshi.Json

data class OrderDetailsObj (
    val id: String,
    val supermarket_id: String,
    val section_id: String,
    var favourite: Boolean?,

    val name: String,
    val type: String,
    var position: Int?,
    val price_before: Double,
    val price_after: Double,
    @Json(name = "img_src") val imgSrcUrl: String
)