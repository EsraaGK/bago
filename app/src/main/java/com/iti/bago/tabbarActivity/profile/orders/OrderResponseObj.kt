package com.iti.bago.tabbarActivity.profile.orders

data class OrderResponseObj(
 var order_id :String,
 var order_status:String,
 var total_price:String,
 var supermarket_name:String,
 var position :Int?
 )

// "order_id": 11,
//        "order_status": "Pinding",
//        "total_price": 2200,
//        "supermarket_name": “carrefour”,