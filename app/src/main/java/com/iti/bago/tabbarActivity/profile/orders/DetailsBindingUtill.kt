package com.iti.bago.tabbarActivity.profile.orders

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ProductItem>?) {

   val adapter = recyclerView.adapter as DetailsOrderAdapter
   adapter.submitList(data)
}