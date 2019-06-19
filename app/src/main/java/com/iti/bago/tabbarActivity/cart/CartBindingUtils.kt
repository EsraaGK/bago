package com.iti.bago.tabbarActivity.cart

import android.databinding.BindingAdapter
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iti.bago.R


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CartObj>?) {
    val adapter = recyclerView.adapter as CartRecyclerAdapter
    adapter.submitList(data)

    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        private val deleteIcon = ContextCompat.getDrawable(recyclerView.context, R.drawable.ic_delete_white)
        private val intrinsicWidth = deleteIcon!!.intrinsicWidth
        private val intrinsicHeight = deleteIcon!!.intrinsicHeight
        private val background = ColorDrawable()
        private val backgroundColor = Color.parseColor("#f44336")
        private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // cartFragmentViewModel.deletefromList(viewHolder.adapterPosition)
            adapter.cartFragmentViewModel.deletefromList(viewHolder.adapterPosition)

            // noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
        }

        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top

            // Draw the red delete background
            background.color = backgroundColor
            background.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            background.draw(canvas)

            // Calculate position of delete icon
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight

            // Draw the delete icon
            deleteIcon!!.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            deleteIcon.draw(canvas)

            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

    }).attachToRecyclerView(recyclerView)


}

@BindingAdapter("cart_image")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = Uri.parse(imgUrl).buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
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

        LoadingStatus.EMPTY -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.mipmap.emptycart)
        }
    }
}

@BindingAdapter("LoadingtxtStatus")
fun bindtxtStatus(txt: TextView, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> {
            txt.visibility = View.GONE
        }
        LoadingStatus.ERROR -> {
            txt.visibility = View.GONE
        }
        LoadingStatus.DONE -> {
            txt.visibility = View.GONE
        }

        LoadingStatus.EMPTY -> {
            txt.visibility = View.VISIBLE
        }
    }
}


@BindingAdapter("LoadingbtnStatus")
fun bindbtnStatus(btn: Button, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> {
            btn.visibility = View.GONE
        }
        LoadingStatus.ERROR -> {
            btn.visibility = View.GONE
        }
        LoadingStatus.DONE -> {
            btn.visibility = View.VISIBLE
        }

        LoadingStatus.EMPTY -> {
            btn.visibility = View.GONE
        }
    }
}

@BindingAdapter("receipt")
fun receipt(layout: ConstraintLayout, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> {
            layout.visibility = View.GONE
        }
        LoadingStatus.ERROR -> {
            layout.visibility = View.GONE
        }
        LoadingStatus.DONE -> {
            layout.visibility = View.VISIBLE
        }

        LoadingStatus.EMPTY -> {
            layout.visibility = View.GONE
        }
    }
}


@BindingAdapter("cart_name")
fun TextView.setProductName(item: String?) {
    item?.let {
        text = item
    }
}


@BindingAdapter("cart_unit_price")
fun TextView.setUnitPrice(item: Double?) {
    item?.let {
        text = item.toString()
    }
}

@BindingAdapter("cart_unit_weight")
fun TextView.setUnitWeight(item: String?) {
    item?.let {
        text = item
    }
}


@BindingAdapter("price_weight")
fun TextView.setPriceXWeight(item: String?) {
    item?.let {
        text = item
    }
}


@BindingAdapter("units_no")
fun TextView.setUnitsno(item: Int?) {
    item?.let {
        text = item.toString()
    }
}

@BindingAdapter("item_total_price")
fun TextView.setFinalPrice(item: Double?) {
    item?.let {
        text = item.toString()
    }
}


@BindingAdapter("favourite_flag")
fun setFav(imageButton: ImageButton, flag: Boolean?) {


    when (flag) {
        false -> {
            imageButton.setImageResource(R.mipmap.heart)
        }
        true -> {
            imageButton.setImageResource(R.drawable.pink_heart)
        }

    }
}