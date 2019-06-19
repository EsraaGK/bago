package com.iti.bago.signup_login

data class UserLoginResponseObj(var customer: UserData, var token: String)







object UserLoginResponse{
    var userLoginResponseObj : UserLoginResponseObj?=null
    init {
     userLoginResponseObj = UserLoginResponseObj(UserData("",
         "" , "" ,"",
         "", "", "", "", "", "", ""), "")
    }

}