package com.iti.bago.tabbarActivity.profile.orders

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.iti.bago.databinding.OrderItemBinding

class OrdersRecyclerAdapter  (val OrdersFragmentViewModel: OrdersFragmentViewModel):
    ListAdapter<OrderResponseObj, OrdersRecyclerAdapter.ViewHolder>(
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
        val binding = OrderItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(
            binding,
            OrdersFragmentViewModel
        )
    }



    class ViewHolder(var binding: OrderItemBinding, val ordersFragmentViewModel: OrdersFragmentViewModel ):
        RecyclerView.ViewHolder(binding.root )
    // , View.OnClickListener
    {



        fun bindObj(orderResponseObj: OrderResponseObj) {

            binding.orderObj = orderResponseObj
             binding.viewModel = ordersFragmentViewModel

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    class StoreDiffCallback :
        DiffUtil.ItemCallback<OrderResponseObj>() {
        override fun areItemsTheSame(oldiItem : OrderResponseObj, newItem: OrderResponseObj): Boolean {

            return oldiItem.order_id === newItem.order_id
        }

        override fun areContentsTheSame(oldItem: OrderResponseObj, newItem: OrderResponseObj): Boolean {
            return oldItem == newItem
        }
    }

}