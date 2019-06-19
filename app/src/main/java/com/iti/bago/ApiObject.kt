package com.iti.bago

data class ApiObject (

  var  name: String ,
  var  supermarket_id :Long,
  var section_id: Long,
  var type :String,   //kilo or piece
  var  price_before :Long,
  var  price_after :Long,
  var  no_of_orders :Long,
  var  photo :String

)

// view?.findNavController()?.navigate(R.customer_id.action_gameWonFragment_to_gameFragment)
//
//lateinit var args:GameWonFragmentArgs

//  view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(
//                            numQuestions, questionIndex))
//<fragment android:customer_id="@+customer_id/gameWonFragment" android:name="com.esraa.navigation_fragments.GameWonFragment"
//              android:label="fragment_game_won" tools:layout="@layout/fragment_game_won">
//        <action android:customer_id="@+customer_id/action_gameWonFragment_to_gameFragment" app:destination="@customer_id/gameFragment"/>
//        <argument android:name="numQuestions" app:argType="integer"/>
//        <argument android:name="numCorrect" app:argType="integer"/>
//    </fragment>