package com.iti.bago.tabbarActivity.profile.orders

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.iti.bago.databinding.ItemOrderProductBinding
class DetailsOrderAdapter(): ListAdapter<ProductItem, DetailsOrderAdapter.ViewHolder>(
    StoreDiffCallback()
) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // var item = cartFragmentViewModel.getItem(position)
        var item = getItem(position)
        item.position = position
        holder.bindObj(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        //Second Here you inflate the view from its layout and pass it in to a PhotoHolder.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderProductBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }



    class ViewHolder(var binding: ItemOrderProductBinding ):
        RecyclerView.ViewHolder(binding.root )
    // , View.OnClickListener
    {



        fun bindObj(productItem: ProductItem) {

            binding.productItem = productItem


            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    class StoreDiffCallback :
        DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldiItem : ProductItem, newItem: ProductItem): Boolean {

            return oldiItem.product_id === newItem.product_id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }

}
