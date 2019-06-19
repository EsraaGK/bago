package com.iti.bago.tabbarActivity.home.store


import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.android.databinding.library.baseAdapters.BR.viewModel
import com.iti.bago.R
import com.iti.bago.databinding.HomeFragmentBinding
import com.iti.bago.databinding.StoresItemBinding

class StoresRecyclerAdapter (val viewModel: HomeFragmentViewModel):
    ListAdapter<StoreObj, StoresRecyclerAdapter.ViewHolder>(StoreDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = getItem(position)
        holder.bindObj(item, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        //Second Here you inflate the view from its layout and pass it in to a PhotoHolder.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StoresItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }




    class ViewHolder(var binding:StoresItemBinding):
        RecyclerView.ViewHolder(binding.root ) {

//        init {
//            binding.root.setOnClickListener(this)
//
//        }

        //4
//        override fun onClick(v: View) {
//
//            var storeId = binding.storeObj!!.customer_id

//           // navController.navigate(R.customer_id.action_homeFragment_to_storeSections)
//
//            Log.d("RecyclerView", storeId)
//        }



        fun bindObj(storeObj: StoreObj, viewModel:HomeFragmentViewModel) {

            binding.storeObj =storeObj
            binding.viewModel= viewModel
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    class StoreDiffCallback :
        DiffUtil.ItemCallback<StoreObj>() {
        override fun areItemsTheSame(oldiItem : StoreObj, newItem: StoreObj): Boolean {

            return oldiItem.id === oldiItem.id
        }

        override fun areContentsTheSame(oldItem: StoreObj, newItem: StoreObj): Boolean {
            return oldItem == newItem
        }
    }
}