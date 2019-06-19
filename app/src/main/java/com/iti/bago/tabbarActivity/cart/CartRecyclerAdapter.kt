package com.iti.bago.tabbarActivity.cart




import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.iti.bago.R
import com.iti.bago.databinding.CartItemBinding
import com.iti.bago.databinding.StoresItemBinding
import com.iti.bago.inflate
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.stores_item.view.*
import android.text.method.TextKeyListener.clear




    class CartRecyclerAdapter (val cartFragmentViewModel: CartFragmentViewModel):
        ListAdapter<CartObj, CartRecyclerAdapter.ViewHolder>(StoreDiffCallback()) {


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           // var item = cartFragmentViewModel.getItem(position)
            var item = getItem(position)
            item.position = position
            holder.bindObj(item)
        }

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

            //Second Here you inflate the view from its layout and pass it in to a PhotoHolder.
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CartItemBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(binding  ,cartFragmentViewModel)
        }



        class ViewHolder(var binding: CartItemBinding, val cartFragmentViewModel: CartFragmentViewModel):
            RecyclerView.ViewHolder(binding.root )
           // , View.OnClickListener
        {



            fun bindObj(cartObj: CartObj) {

                binding.cartObj = cartObj
                binding.viewModel = cartFragmentViewModel

                // This is important, because it forces the data binding to execute immediately,
                // which allows the RecyclerView to make the correct view size measurements
                binding.executePendingBindings()
            }
        }


        class StoreDiffCallback :
            DiffUtil.ItemCallback<CartObj>() {
            override fun areItemsTheSame(oldiItem : CartObj, newItem: CartObj): Boolean {

                return oldiItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: CartObj, newItem: CartObj): Boolean {
                return oldItem == newItem
            }
        }
    }