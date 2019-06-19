package com.iti.bago.signup_login.onboarding

data class SignupResponseObj (var customer :Customer)
data class Customer(
    var name :String,
    var email :String,
    var password :String,
    var id :String
)











//"customer": {
//    "name": "esraa",
//    "email": "as@gmail.com",
//    "password": "$2y$10$6YdY4WepGwTkYvgESn624uDCh.VnHeTzCEEWnb6VlbW3Jnr87AnjC",
//    "updated_at": "2019-06-11 19:52:20",
//    "created_at": "2019-06-11 19:52:20",
//    "customer_id": 10
//}