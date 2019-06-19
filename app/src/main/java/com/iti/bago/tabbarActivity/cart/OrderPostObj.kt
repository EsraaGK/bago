package com.iti.bago.tabbarActivity.cart

data class OrderPostObj(
    val user_address: String,
    val user_phone :String,
    val total_price: Float,
    val supermarket_id: String,
    val customer_id: String,
    val products :List<CartObj>
)

