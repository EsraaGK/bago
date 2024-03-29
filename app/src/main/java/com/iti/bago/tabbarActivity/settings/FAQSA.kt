package com.iti.bago.tabbarActivity.settings


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import com.iti.bago.R
import com.iti.bago.tabbarActivity.TabbarActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FAQSA : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    internal var expandableListView1: ExpandableListView? = null
    internal var expandableListView2: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String> ? = null


    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val Lorem_ipsum1 = ArrayList<String>()
            Lorem_ipsum1.add("First, you need to add a product to your cart\n" +
                    "        , add a product to your cart by selecting the cart icon, find your cart at the menu and complete your purchase\n" +
                    "        scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into\n" )

            val Lorem_ipsum2 = ArrayList<String>()
            Lorem_ipsum2.add("First, you need to add a product to your cart\n" +
                    "        , add a product to your cart by selecting the cart icon, find your cart at the menu and complete your purchase\n" +
                    "        scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into\n")

            val Lorem_ipsum3 = ArrayList<String>()
            Lorem_ipsum3.add("\"First, you need to add a product to your cart\\n\" +\n" +
                    "                    \"        , add a product to your cart by selecting the cart icon, find your cart at the menu and complete your purchase\\n\" +\n" +
                    "                    \"        scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into\\n\"")


            listData[" How to make orders?"] = Lorem_ipsum1
            listData["Lorem ipsum?"] = Lorem_ipsum2
     //       listData["Lorem ipsum3?"] = Lorem_ipsum3
//            listData["Lorem ipsum4?"] = Lorem_ipsum1
//            listData["Lorem ipsum5?"] = Lorem_ipsum2
//            listData["Lorem ipsum6?"] = Lorem_ipsum3


            return listData
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as TabbarActivity).setFragTitle("FAQSA")
        return inflater.inflate(R.layout.fragment_faqs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //expandableListView1 = findViewById(R.customer_id.expandableListView1)
        if (expandableListView1 != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = ExpandableListAdapter(this.context!!, titleList as ArrayList<String>, listData)
            expandableListView1!!.setAdapter(adapter)
        }

      //  expandableListView2 = findViewById(R.customer_id.expandableListView2)
        if (expandableListView2 != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = ExpandableListAdapter(this.context!!, titleList as ArrayList<String>, listData)
            expandableListView2!!.setAdapter(adapter)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.title = "FAQS"

        expandableListView1 = activity!!.findViewById(R.id.expandableListView1)
        if (expandableListView1 != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = ExpandableListAdapter(activity!!.applicationContext, titleList as ArrayList<String>, listData)
            expandableListView1!!.setAdapter(adapter)
        }

//        expandableListView2 = activity!!.findViewById(R.id.expandableListView2)
//        if (expandableListView2 != null) {
//            val listData = data
//            titleList = ArrayList(listData.keys)
//            adapter = ExpandableListAdapter(activity!!.applicationContext, titleList as ArrayList<String>, listData)
//            expandableListView2!!.setAdapter(adapter)
//        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FAQSA().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



