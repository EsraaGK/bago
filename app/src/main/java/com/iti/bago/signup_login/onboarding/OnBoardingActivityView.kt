package com.iti.bago.signup_login.onboarding

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.iti.bago.R
import android.widget.Toast
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.os.Build
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import com.iti.bago.tabbarActivity.TabbarActivity


class OnBoardingActivityView : AhoyOnboarderActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     setContentView(R.layout.activity_on_boarding_view)

        val ahoyOnboarderCard1 =
            AhoyOnboarderCard(
                R.string.ob_header1,
                R.string.ob_desc1,
                R.mipmap.walk1)
        val ahoyOnboarderCard2 = AhoyOnboarderCard(
            R.string.ob_header2,
            R.string.ob_desc2,
            R.mipmap.walk2
        )
        val ahoyOnboarderCard3 = AhoyOnboarderCard(
            R.string.ob_header3,
            R.string.ob_desc3,
            R.mipmap.walk3
        )

        ahoyOnboarderCard1.setBackgroundColor(R.color.white)
        ahoyOnboarderCard2.setBackgroundColor(R.color.white)
        ahoyOnboarderCard3.setBackgroundColor(R.color.white)

        val pages = arrayListOf<AhoyOnboarderCard>()

        pages.add(ahoyOnboarderCard1)
        pages.add(ahoyOnboarderCard2)
        pages.add(ahoyOnboarderCard3)

        for (page in pages) {
            page.setTitleColor(R.color.headerscolor)
            page.setDescriptionColor(R.color.secondry)
            page.setTitleTextSize(dpToPixels(10, this));
            page.setDescriptionTextSize(dpToPixels(6, this));
            //page.setIconLayoutParams(width, height, marginTop, marginLeft, marginRight, marginBottom);
        }

        setFinishButtonTitle("Finish")
        showNavigationControls(true)
        setGradientBackground()

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button))
        }

//        val face = Typeface.createFromAsset(assets, "fonts/Roboto-Light.ttf")
  //      setFont(face)

        setOnboardPages(pages)
    }

    override fun onFinishButtonPressed() {
        startActivity(Intent(this, TabbarActivity::class.java))
        finish()
    }
}