package com.iti.bago.tabbarActivity.settings

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iti.bago.R
import com.iti.bago.tabbarActivity.TabbarActivity

class WebViewFragment : Fragment() {

    companion object {
        fun newInstance() = WebViewFragment()
    }
  lateinit  var  toolbar: ActionBar
    private lateinit var viewModel: WebView_ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as TabbarActivity).setFragTitle("About")

        return inflater.inflate(R.layout.web_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebView_ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
