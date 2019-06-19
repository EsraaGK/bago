package com.iti.bago.tabbarActivity.home.storesections

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.iti.bago.R
import com.iti.bago.databinding.StoresectionItemBinding



    class StoreSectionsRecyclerAdapter (public var navController: NavController,val storeSectionsViewModel: StoreSectionsViewModel):
        ListAdapter<StoreSectionsObj, StoreSectionsRecyclerAdapter.ViewHolder>(StoreDiffCallback()) {
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var item = getItem(position)
            holder.bindObj(item, storeSectionsViewModel)
        }

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

            //Second Here you inflate the view from its layout and pass it in to a PhotoHolder.
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = StoresectionItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding, navController)
        }




        class ViewHolder(var binding: StoresectionItemBinding, var navController: NavController):
            RecyclerView.ViewHolder(binding.root )//, View.OnClickListener {
        {
//            init {
//                binding.root.setOnClickListener(this)
//
//            }
//
//            //4
//            override fun onClick(v: View) {
//                Log.d("RecyclerView", "CLICK!")
//
//                navController.navigate(R.customer_id.action_storeSections_to_productsFragment)
//
//            }
//
//            companion object {
//                //5
//                private val PHOTO_KEY = "PHOTO"
//            }


            fun bindObj(storeSectionsObj: StoreSectionsObj, storeSectionsViewModel:StoreSectionsViewModel) {

                binding.storesectionObj =storeSectionsObj
                binding.viewModel = storeSectionsViewModel
                // This is important, because it forces the data binding to execute immediately,
                // which allows the RecyclerView to make the correct view size measurements
                binding.executePendingBindings()
            }
        }


        class StoreDiffCallback :
            DiffUtil.ItemCallback<StoreSectionsObj>() {
            override fun areItemsTheSame(oldiItem : StoreSectionsObj, newItem: StoreSectionsObj): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                return oldiItem.id === oldiItem.id
            }

            override fun areContentsTheSame(oldItem: StoreSectionsObj, newItem: StoreSectionsObj): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                return oldItem == newItem
            }
        }
    }