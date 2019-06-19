package com.iti.bago.signup_login.onboarding

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController

import com.iti.bago.R

class OnboardingFragment : Fragment() {

    companion object {
        var COMPLETED_ONBOARDING_PREF_NAME: String = "on_boarding_complete"
    }

    private var pagerIndicator: LinearLayout? = null
    private var dotsCount: Int = 0
    private lateinit var dots: Array<ImageView?>


    private var onBoardPager: ViewPager? = null

    private var mAdapter: OnBoard_Adapter? = null

    private var btnGetStarted: Button? = null

    internal var previousPos = 0


    private var onBoardItems = ArrayList<OnBoardItem>()

    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.onboarding_fragment, container, false)

        var navController = this.findNavController()

        btnGetStarted = view.findViewById(R.id.btn_get_started)
        onBoardPager = view.findViewById(R.id.pager_introduction)
        pagerIndicator = view.findViewById(R.id.viewPagerCountDots)

        loadData()

        mAdapter = OnBoard_Adapter(this.context!!, onBoardItems)
        onBoardPager!!.adapter = mAdapter
        onBoardPager!!.currentItem = 0
        onBoardPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                // Change the current position intimation

                for (i in 0 until dotsCount) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            context!!,
                            R.drawable.non_selected_item_dot
                        )
                    )
                }

                dots[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        context!!,
                        R.drawable.selected_item_dot
                    )
                )


                val pos = position + 1

                if (pos == dotsCount && previousPos == dotsCount - 1)
                    showAnimation()
                else if (pos == dotsCount - 1 && previousPos == dotsCount)
                    hideAnimation()

                previousPos = pos
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        btnGetStarted!!.setOnClickListener {
            navController.navigate(R.id.action_onboardingFragment_to_tabbarActivity)
        }

        setUiPageViewController()



        return view
    }
    // Load data into the viewpager

    fun loadData() {

        val header = intArrayOf(R.string.ob_header1, R.string.ob_header2, R.string.ob_header3)
        val desc = intArrayOf(R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3)
        val imageId = intArrayOf(R.mipmap.walk1, R.mipmap.walk2, R.mipmap.walk3)

        for (i in imageId.indices) {
            val item = OnBoardItem()
            item.imageID = imageId[i]
            item.title = resources.getString(header[i])
            item.description = resources.getString(desc[i])

            onBoardItems.add(item)
        }
    }

    // Button bottomUp animation

    fun showAnimation() {
        val show = AnimationUtils.loadAnimation(this.context, R.anim.slide_up_anim)

        btnGetStarted!!.startAnimation(show)

        show.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                btnGetStarted!!.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {

                btnGetStarted!!.clearAnimation()

            }

        })


    }

    // Button Topdown animation

    fun hideAnimation() {
        val hide = AnimationUtils.loadAnimation(this.context, R.anim.slide_down_anim)

        btnGetStarted!!.startAnimation(hide)

        hide.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {

                btnGetStarted!!.clearAnimation()
                btnGetStarted!!.visibility = View.GONE

            }

        })


    }

    // setup the
    private fun setUiPageViewController() {

        dotsCount = mAdapter!!.count
        dots = arrayOfNulls(dotsCount)

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(this.context)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this.context!!,
                    R.drawable.non_selected_item_dot
                )
            )

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(6, 0, 6, 0)

            pagerIndicator!!.addView(dots[i], params)
        }

        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.selected_item_dot))
    }

    override fun onDestroy() {
        super.onDestroy()
        // User has seen OnBoardingActivity, so mark our SharedPreferences
        // flag as completed so that we don't show our OnBoardingActivity
        // the next time the customer launches the app.
        PreferenceManager.getDefaultSharedPreferences(this.context).edit().apply {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
            apply()
        }
    }
}
