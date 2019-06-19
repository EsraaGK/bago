package com.iti.bago.tabbarActivity.home.store

import android.databinding.BindingAdapter
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iti.bago.R



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<StoreObj>?) {
    val adapter = recyclerView.adapter as StoresRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("store_image")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = Uri.parse(imgUrl).buildUpon().scheme("https").build()
//        val imgUri1 = imgUrl.replace("https","http")
//        Log.i("xx","${imgUri1}")
////        val imgUri = Uri.parse(imgUri1).buildUpon().scheme("http").build()
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


@BindingAdapter("store_name")
fun TextView.setProductName(item: String?) {
    item?.let {
        text = item
    }
}
