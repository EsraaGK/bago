package com.iti.bago.tabbarActivity.home.product


import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.iti.bago.databinding.ProductItemBinding


class productRecyclerAdapter(public var navController: NavController, val viewModel: ProductsFragmentViewModel) :
    ListAdapter<ProductObj, productRecyclerAdapter.ViewHolder>(ProductDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = getItem(position)
        item.position = position
        holder.bindObj(item, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        //Second Here you inflate the view from its layout and pass it in to a PhotoHolder.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, navController)
    }


    class ViewHolder(var binding: ProductItemBinding, var navController: NavController) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindObj(productObj: ProductObj, viewModel: ProductsFragmentViewModel) {

            binding.productObj = productObj
            binding.viewModel = viewModel
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    class ProductDiffCallback :
        DiffUtil.ItemCallback<ProductObj>() {
        override fun areItemsTheSame(oldiItem: ProductObj, newItem: ProductObj): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            return oldiItem.id === oldiItem.id
        }

        override fun areContentsTheSame(oldItem: ProductObj, newItem: ProductObj): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return oldItem == newItem
        }
    }
}