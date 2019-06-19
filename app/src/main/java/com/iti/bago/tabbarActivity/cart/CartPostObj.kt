package com.iti.bago.tabbarActivity.cart

import java.sql.SQLTransactionRollbackException

data class CartPostObj(
    val supermarket_id: String,
    val product_id: String,
    val units_no: Int,
    val customer_id: String,
    val message:String? = ""
)