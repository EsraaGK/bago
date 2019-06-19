package com.iti.bago.signup_login.onboarding

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.iti.bago.R

import java.util.ArrayList

class OnBoard_Adapter(mContext: Context, items: ArrayList<OnBoardItem>) : PagerAdapter() {

    private var mContext: Context? = null
    var onBoardItems: ArrayList<OnBoardItem> = ArrayList()


    init {
        this.mContext = mContext
        this.onBoardItems = items
    }

    override fun getCount() = onBoardItems.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var itemView:View = LayoutInflater.from (mContext).inflate(R.layout.onboard_item, container, false)

        var item: OnBoardItem = onBoardItems[position]

        var imageView:ImageView  =itemView.findViewById(R.id.iv_onboard)
        imageView.setImageResource(item.imageID)

        var tv_title:TextView  = itemView.findViewById (R.id.tv_header)
        tv_title.text = item.title

        var tv_content:TextView  =itemView.findViewById(R.id.tv_desc)
        tv_content.text = item.description

        container.addView(itemView)

        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}