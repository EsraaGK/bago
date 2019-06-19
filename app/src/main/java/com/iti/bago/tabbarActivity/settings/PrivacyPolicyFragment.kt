package com.iti.bago.tabbarActivity.settings


import android.drm.DrmStore
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
class PrivacyPolicyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as TabbarActivity).setFragTitle("Privacy Policy")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacypolicy, container, false)
    }


}
