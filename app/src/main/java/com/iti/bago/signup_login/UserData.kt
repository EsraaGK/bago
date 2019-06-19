package com.iti.bago.signup_login

data class UserData(
    var id: String?,
    var email: String?,
    var mobile: String?,
    var longitude: String?,
    var latitude: String?,
    var country_code: String?,
    var phone_number: String?,
    var no_of_orders: String?,
    var name: String?,
    var address: String?,
    var new_user_flag: String?
)


//"customer_id": 5,
//        "name": "x",
//        "email": "x@gmail.com",
//        "password": "$2y$10$hEU6nb.NrYrpztnY1uVn5utFr508JRvDRahDbRQPS7hqlI7ix7SSO",
//        "address": null,
//        "longitude": null,
//        "latitude": null,
//        "country_code": null,
//        "phone_number": null,
//        "status": 1,
//        "no_of_orders": 0,
//        "facebook_access_token": null,
//        "twitter_access_token": null,
//        "created_at": "2019-06-11 18:41:27",
//        "updated_at": "2019-06-11 18:43:35",
//        "api_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9iYWdvLmlidGlrYXIubmV0LnNhXC9hcGlcL2N1c3RvbWVyXC9sb2dpbiIsImlhdCI6MTU2MDI3ODYxNSwiZXhwIjoxNTYwMjgyMjE1LCJuYmYiOjE1NjAyNzg2MTUsImp0aSI6IjV1SUgzemJkN0hiVG56aFQiLCJzdWIiOjUsInBydiI6IjFkMGEwMjBhY2Y1YzRiNmM0OTc5ODlkZjFhYmYwZmJkNGU4YzhkNjMifQ.8nODUPS7C9Ovt_P_HUrADIWWem71xht4R66a307eYRY",
//        "new_user_flag": "true"