package com.iti.bago.tabbarActivity.profile.favourites

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.iti.bago.databinding.FavouriteItemBinding

class FavouritesRecyclerAdapter (val favouritesViewModel: FavouritesViewModel):
    ListAdapter<FavouritesObj, FavouritesRecyclerAdapter.ViewHolder>(
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
        val binding = FavouriteItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(
            binding,
            favouritesViewModel
        )
    }



    class ViewHolder(var binding: FavouriteItemBinding, val favouritesViewModel: FavouritesViewModel):
        RecyclerView.ViewHolder(binding.root )
    // , View.OnClickListener
    {



        fun bindObj(favouritesObj: FavouritesObj) {

            binding.favouritesObj = favouritesObj
            binding.viewModel = favouritesViewModel

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    class StoreDiffCallback :
        DiffUtil.ItemCallback<FavouritesObj>() {
        override fun areItemsTheSame(oldiItem : FavouritesObj, newItem: FavouritesObj): Boolean {

            return oldiItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: FavouritesObj, newItem: FavouritesObj): Boolean {
            return oldItem == newItem
        }
    }
}