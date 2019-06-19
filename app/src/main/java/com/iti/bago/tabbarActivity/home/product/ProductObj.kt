package com.iti.bago.tabbarActivity.home.product

import com.squareup.moshi.Json

data class ProductObj(
    val id: String,
    // used to map img_src from the JSON to img_src in our class
    @Json(name = "img_src") val imgSrcUrl: String?,
    val name: String,
    val type: String,
    val price_before: Double,
    val price_after: Double,
    val supermarket_id: String,
    val section_id :String,
    var position :Int?
)

//"id": 1,
//        "name": "sam1",
//        "type": "piece",
//        "price_before": 1000,
//        "price_after": 990,
//        "no_of_orders": 4,
//        "photo": "sam1",
//        "supermarket_id": 1,
//        "section_id": 1,
//        "created_at": null,
//        "updated_at": null,
//        "deleted_at": null
//
//customer_id 		=> bigint
//name 		=> string
//type		=> enum('piece', 'kilo')
//price_before	=> float
//price_after	=> float
//no_of_orders	=> integer
//img_url 	=> string
//supermarket_id	=> bigint
//section_id	=> bigint
//created_at	=> timestamp
//updated_at	=> timestamp
//deleted_at	=> timestamp
//

//val customer_id :String
//val name :String
//val type:String
//val price_before :Double
//val price_after	:Double
//val img_url :String
//val supermarket_id	:String
//val section_id	:String

