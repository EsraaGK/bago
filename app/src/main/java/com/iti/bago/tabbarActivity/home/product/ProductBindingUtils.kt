package com.iti.bago.tabbarActivity.home.product

import android.databinding.BindingAdapter
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iti.bago.R
import kotlinx.android.synthetic.main.product_item.view.*

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ProductObj>?) {
    val adapter = recyclerView.adapter as productRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("product_image")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = Uri.parse(imgUrl).buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("LoadingStatus")
fun bindStatus(statusImageView: ImageView, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        LoadingStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        LoadingStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


@BindingAdapter("product_name")
fun TextView.setProductName(item: String?) {
    item?.let {
        text = item
    }
}

@BindingAdapter("product_unit_weight")
fun TextView.setProductUnitWeigh(item: String?) {
    item?.let {
        text = item
    }
}

@BindingAdapter("product_unit_price")
fun TextView.setProductUnitPrice(item: Double?) {
    item?.let {
        text = item.toString()
    }
}