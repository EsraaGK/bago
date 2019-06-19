package com.iti.bago.tabbarActivity.profile.orders

import android.arch.lifecycle.MutableLiveData

class DetailsResponseObj (
 var order_id:String,
 var status:String,
 var payment_type:String?,
 var created_at:String,
 var total_price:String,
 var supermarket_name:String,
 var lng_src:String,
 var lat_src:String,
 var delivery_phone:String,
 var customer_address:String,
 var lng_dest:String,
 var lat_dest:String,
 var products:List<ProductItem>
 //MutableLiveData<List<ProductItem>>?
)

data class ProductItem (
    var units_no :String,
    var price_after :String,
    var product_id :String,
    var product_name :String?,
    var position :Int?

)