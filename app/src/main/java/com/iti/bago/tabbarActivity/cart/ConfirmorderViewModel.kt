package com.iti.bago.tabbarActivity.cart


import android.app.AlertDialog
import android.arch.lifecycle.ViewModel;
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.iti.bago.R
import com.iti.bago.tabbarActivity.ConfirmRetrofitObj
import kotlinx.android.synthetic.main.confirm_dialog.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ConfirmorderViewModel(val tempArray: Array<CartObj>) : ViewModel() {
    lateinit var customer_id: String
    lateinit var customer_token: String


    val date = getCurrentDateTime()
    val current_date = date.toString("yyyy/MM/dd HH:mm:ss")

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var messyList = tempArray.toList()
    var adress: String = ""
    var phone: String = ""
    var mySupermarketsId = messyList.distinctBy { it.supermarket_id }
    val supermarkets_no = mySupermarketsId.size

var args :ConfirmOrderFragmentArgs?= null
   // val total_orders_coast = (args!!.totalPrice) *15
    fun setId_Token(id: String, token: String) {
        customer_id = id
        customer_token = token
    }

//1
//    fun ConfirmPostOrder(v: View) {
//
//
//    }


    //2
    fun ConfirmPostOrder(v: View) {
        var flag = false
        adress = v.rootView.findViewById<EditText>(R.id.address_txt).editableText.toString()
        phone = v.rootView.findViewById<EditText>(R.id.phone_txt).editableText.toString().substring(1)

        val number = validNumber(phone, "+20")

        if ((adress.isEmpty() || adress.isBlank()) && number == null) {
            if ((adress.isEmpty() || adress.isBlank())) {
                v.rootView.findViewById<EditText>(R.id.phone_txt).error = "Invalid Data"
            }

            if (number == null) {
                v.rootView.findViewById<EditText>(R.id.phone_txt).error = "Invalid Data"
            }
            //error

        } else {
            //data is full ...Disable button
            v.rootView.findViewById<Button>(R.id.confirm_btn).isEnabled = false
            // create the thing to store the sub lists
            val subs = HashMap<String, MutableList<CartObj>>()
            // iterate through your objects
            for (o in messyList) {
                // fetch the list for this object's id
                var temp: MutableList<CartObj>? = subs[o.supermarket_id]
                if (temp == null) {
                    // if the list is null we haven't seen an
                    // object with this id before, so create
                    // a new list
                    temp = ArrayList()

                    // and add it to the map
                    subs[o.supermarket_id] = temp
                }

                // whether we got the list from the map
                // or made a new one we need to add our
                // object.
                temp.add(o)
            }

            for (n in mySupermarketsId) {
                val postOrdersList = subs[n.supermarket_id]
                val total_price = calcTotalPrice(postOrdersList!!.toList())
                Log.i("Super Market", "${n.supermarket_id} " + "\n ----------------------------------")
                val orderPostObj = OrderPostObj(
                    this.adress, this.phone,
                    total_price, n.supermarket_id, customer_id, postOrdersList
                )
                //3
                Log.i("confirm order", "${customer_id} " + "")
                //post fun
                posttoServer(orderPostObj, v)
            }

        }


}

fun calcTotalPrice(list: List<CartObj>): Float {
    var total_price = 0.0F

    if (list.size != 0) {
        for (n in list) {

            total_price += n.item_total_price.toFloat()
            Log.i("Product : ", "${n.name} \n")
        }
    } else {
        total_price = 0.0F
    }
    return total_price
}

fun posttoServer(orderPostObj: OrderPostObj, v: View) {
    coroutineScope.launch {
        val cartPostResponseObj = ConfirmRetrofitObj.retrofitSercice.postConfirmOrder(
            orderPostObj
            //, customer_token
        )
        try {
            // this will run on a thread managed by Retrofit
            val PostResponseObj = cartPostResponseObj.await()
            Toast.makeText(v.context, "order is succedded", Toast.LENGTH_SHORT).show()
            v.findNavController().navigate(R.id.action_confirmOrderFragment_to_homeFragment)
            Log.i("order is succedded", "Product is SUCCESSFULLY added to Favourites !")
        } catch (e: Exception) {
            Log.i("error", "${e.message}")


        }

    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}


override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
}

fun showDialog(view: View) {
    //Inflate the dialog with custom view
    val mDialogView = LayoutInflater.from(view.context).inflate(R.layout.confirm_dialog, null)
    //AlertDialogBuilder
    val mBuilder = AlertDialog.Builder(view.context)
        .setView(mDialogView)
        .setTitle("Confirm Order")
    //show dialog
    val mAlertDialog = mBuilder.show()

    //set text from EditTexts of custom layout
    mDialogView.supermarket_text.text = "You Will Order From $supermarkets_no " +
            "Supermarkets with total price of {${args!!.totalPrice +(supermarkets_no* 15)}} ,Order?"

    //ok button click of custom layout
    mDialogView.dialogOkBtn.setOnClickListener {
        //set the input text in TextView

        //dismiss dialog
        ConfirmPostOrder(view)
        mAlertDialog.dismiss()

    }
    //cancel button click of custom layout
    mDialogView.dialogCancelBtn.setOnClickListener {
        //dismiss dialog
        mAlertDialog.dismiss()
    }
}


fun validNumber(input: String, countryCode: String): String? {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    val isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode))
    var phoneNumber: Phonenumber.PhoneNumber? = null
    var finalNumber: String? = null
    var isValid = false
    var isMobile: PhoneNumberUtil.PhoneNumberType? = null
    try {
        phoneNumber = phoneNumberUtil.parse(input, isoCode)
        isValid = phoneNumberUtil.isValidNumber(phoneNumber)
        isMobile = phoneNumberUtil.getNumberType(phoneNumber)

    } catch (e: NumberParseException) {
        e.printStackTrace()
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }



    if (isValid && (PhoneNumberUtil.PhoneNumberType.MOBILE == isMobile || PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE == isMobile)) {
        finalNumber = phoneNumberUtil.format(
            phoneNumber,
            PhoneNumberUtil.PhoneNumberFormat.E164
        ).substring(1)
    }

    return finalNumber
}


}
