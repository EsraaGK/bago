package com.iti.bago

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.iti.bago.signup_login.UserData
import com.iti.bago.signup_login.UserLoginResponseObj

object SharedPrefObj {
    var KEY_EMAIL = "email"
    // var KEY_PASSWORD = ""
    var KEY_ID = "customer_id"
    var KEY_LONGITUDE = "longitude"
    var KEY_LATITUDE = "latitude"
    var KEY_COUNTRYCODE = "country_code"
    var KEY_PHONENUMBER = "phone_number"
    var KEY_ADDRESS = "address"
    var KEY_NEW_USER_FLAG = "new_user_flag"
    var KEY_Token = "customer_token"
    var KEY_NAME = "name"


}


class SharedPrefUtil {
    fun saveEmail(email: String, context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_EMAIL, email)
        prefsEditor.apply()
    }

    fun getEmail(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_EMAIL, null)
    }

    fun savePhoneNumber(phoneNumber: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_PHONENUMBER, phoneNumber)
        prefsEditor.apply()
        return true
    }

    fun getPhoneNumber(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_PHONENUMBER, null)
    }

    fun getName(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_NAME, null)
    }

    fun saveName(name: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_NAME, name)
        prefsEditor.apply()
        return true
    }


    fun saveAddress(address: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_ADDRESS, address)
        prefsEditor.apply()
        return true
    }

    fun getAddress(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_ADDRESS, null)
    }

    fun saveNewUserFlag(newUserFlag: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_NEW_USER_FLAG, newUserFlag)
        prefsEditor.apply()
        return true
    }

    fun getNewUserFlag(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_NEW_USER_FLAG, null)
    }

    fun saveLongitudeLatitude(longitude: String, latitude: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_LONGITUDE, longitude)
        prefsEditor.putString(SharedPrefObj.KEY_LATITUDE, latitude)
        prefsEditor.apply()
        return true
    }

    fun getLongitude(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_LONGITUDE, null)
    }

    fun getLatitude(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_LATITUDE, null)
    }

    fun saveCountryCode(countryCode: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_COUNTRYCODE, countryCode)
        prefsEditor.apply()
        return true
    }

    fun getCountryCode(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_COUNTRYCODE, null)
    }

    fun saveId(id: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_ID, id)
        prefsEditor.apply()
        return true
    }

    fun getId(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_ID, null)
    }

    fun saveToken(token: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_Token, token)
        prefsEditor.apply()
        return true
    }

    fun getToken(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(SharedPrefObj.KEY_Token, null)
    }

    fun saveObj(obj: UserLoginResponseObj?, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(SharedPrefObj.KEY_Token, obj!!.token)
        prefsEditor.putString(SharedPrefObj.KEY_ID, obj.customer.id)
        prefsEditor.putString(SharedPrefObj.KEY_ADDRESS, obj.customer.address)
        prefsEditor.putString(SharedPrefObj.KEY_NEW_USER_FLAG, obj.customer.new_user_flag)
        prefsEditor.putString(SharedPrefObj.KEY_NAME, obj.customer.name)
        prefsEditor.putString(SharedPrefObj.KEY_LONGITUDE, obj.customer.longitude)
        prefsEditor.putString(SharedPrefObj.KEY_LATITUDE, obj.customer.latitude)
        prefsEditor.putString(SharedPrefObj.KEY_ADDRESS, obj.customer.address)
        prefsEditor.putString(SharedPrefObj.KEY_COUNTRYCODE, obj.customer.country_code)
        prefsEditor.putString(SharedPrefObj.KEY_PHONENUMBER, obj.customer.phone_number)
        prefsEditor.apply()
        // Log.i("saveObj",SharedPrefObj.KEY_ID)
        return true
    }

    fun getObj(context: Context): UserLoginResponseObj? {
        var userLoginResponseObj: UserLoginResponseObj = UserLoginResponseObj(
            UserData(
                "",
                "", "", "",
                "", "", "", "", "", "", ""
            ), ""
        )
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        userLoginResponseObj.customer.id = prefs.getString(SharedPrefObj.KEY_ID, null)
        userLoginResponseObj.customer.address = prefs.getString(SharedPrefObj.KEY_ADDRESS , null)
        userLoginResponseObj.customer.country_code = prefs.getString(SharedPrefObj.KEY_COUNTRYCODE , null)
        userLoginResponseObj.customer.phone_number = prefs.getString(SharedPrefObj.KEY_PHONENUMBER , null)
        userLoginResponseObj.customer.email = prefs.getString(SharedPrefObj.KEY_EMAIL , null)
        userLoginResponseObj.customer.latitude = prefs.getString(SharedPrefObj.KEY_LATITUDE , null)
        userLoginResponseObj.customer.longitude = prefs.getString(SharedPrefObj.KEY_LONGITUDE , null)
        userLoginResponseObj.customer.name = prefs.getString(SharedPrefObj.KEY_NAME , null)
        userLoginResponseObj.customer.new_user_flag = prefs.getString(SharedPrefObj.KEY_NEW_USER_FLAG , null)
        userLoginResponseObj.token = prefs.getString(SharedPrefObj.KEY_Token , null)

return userLoginResponseObj
    }
}