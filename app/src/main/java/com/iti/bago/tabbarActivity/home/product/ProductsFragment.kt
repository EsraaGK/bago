package com.iti.bago.tabbarActivity.home.product


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


import com.iti.bago.R
import com.iti.bago.SharedPrefUtil
import com.iti.bago.tabbarActivity.TabbarActivity
import kotlinx.android.synthetic.main.products_fragment.view.*

class ProductsFragment : Fragment() {

    private lateinit var viewModel: ProductsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.iti.bago.databinding.ProductsFragmentBinding>(
            inflater, R.layout.products_fragment, container, false
        )
        viewModel = ViewModelProviders.of(this).get(ProductsFragmentViewModel::class.java)


       val args = ProductsFragmentArgs.fromBundle(arguments!!)
        viewModel.args = args
        //assign the activity lifecycle to the xml through the binding
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.position.observe(this, Observer {
            (binding.productsGrid.adapter)?.notifyItemChanged(it!!)
        })

        val navController = this.findNavController()
        binding.productsGrid.adapter = productRecyclerAdapter(navController, viewModel)
       // binding.saleProducts.adapter = productRecyclerAdapter(navController, viewModel)


        val sharedPrefUtil = SharedPrefUtil()
        val id = sharedPrefUtil.getId(this.context!!)
        val token = sharedPrefUtil.getToken(this.context!!)
        viewModel.setId_Token(id!!, token!!)

        //horizontal
//      binding.sectionsrecyclerViewHorizontal.layoutManager = LinearLayoutManager(
//          this.context, LinearLayoutManager.HORIZONTAL, false
////        )
////
//
//        var horizontalAdapter = StoresRecyclerAdapter(navController)
//        binding.sectionsrecyclerViewHorizontal.adapter = horizontalAdapter
//
//        viewModel.apiList.observe(this, Observer<List<ApiObject>> {
//            horizontalAdapter.submitList(it)
//        })

        //vertical
//        binding.sectionsrecyclerViewVertical.layoutManager = GridLayoutManager(
//            activity, 2,
//            GridLayoutManager.VERTICAL, false
//        )
//        var verticalAdapter = StoresRecyclerAdapter(navController)

//        viewModel.apiList.observe(this, Observer<List<ApiObject>> {
//            verticalAdapter.submitList(it)
//        })
        (activity as TabbarActivity).setFragTitle("Products")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
