package com.iti.bago.tabbarActivity.cart

data class OrderObj(

    val id: String,
    val rate: String,
    val status: String,
    val payment_type: String = "Cash",
    val deliverd_at: String,
    val total_price: String,
    val supermarket_name: String,
    val lng_src: String,
    val lat_src: String,
    val delivery_phone: String,
    val customer_address: String,
    val lng_dest: String,
    val lat_dest: String


)