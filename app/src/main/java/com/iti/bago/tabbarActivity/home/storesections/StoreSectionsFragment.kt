package com.iti.bago.tabbarActivity.home.storesections

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.iti.bago.R
import com.iti.bago.tabbarActivity.TabbarActivity

class StoreSectionsFragment : Fragment() {


    private lateinit var viewModel: StoreSectionsViewModel

    lateinit var args: StoreSectionsFragmentArgs
    //Fetching Arguments


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.iti.bago.databinding.StoresectionsFragmentBinding>(
            inflater, R.layout.storesections_fragment, container, false
        )

        viewModel = ViewModelProviders.of(this).get(StoreSectionsViewModel::class.java)
        args = StoreSectionsFragmentArgs.fromBundle(arguments!!)
        viewModel.args = args

        val navController = this.findNavController()
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        var layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)


        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position + 1) % 3 == 0) 2 else 1
            }
        }

        binding.sectionsRecyclerview.layoutManager = layoutManager
        binding.sectionsRecyclerview.adapter =
            StoreSectionsRecyclerAdapter(navController, viewModel)
        (activity as TabbarActivity).setFragTitle("Store Sections")
        return binding.root
    }

}
