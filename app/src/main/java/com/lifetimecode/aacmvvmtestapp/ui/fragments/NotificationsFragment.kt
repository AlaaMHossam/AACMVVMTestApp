package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.lifetimecode.aacmvvmtestapp.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cl_notif_main.setOnClickListener {
            if (textView3.visibility == View.GONE)
                expandAnimation()
            else collapseAnimation()

     //       cl_main.invalidate()
        }
    }

    private fun expandAnimation() {
        textView3.visibility = View.VISIBLE
        roundedImageView.scaleType = ImageView.ScaleType.FIT_XY

        fl_image
            .animate()
            //    .scaleX(1f)
            .scaleY(1.2f)
            .setInterpolator(OvershootInterpolator(1.5f)).setDuration(300)
            .start()

        /*  cl_notif_main
              .animate()
              //    .scaleX(1f)
              .scaleY(1f)
              .setInterpolator(OvershootInterpolator()).setDuration(300)
              .start()*/

        textView3
            .animate()
            .translationY(1.2f)
            .alpha(1f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(300)
            .setStartDelay(100)
            .start()
    }

    private fun collapseAnimation() {
        roundedImageView.scaleType = ImageView.ScaleType.CENTER_CROP

        /*  cl_notif_main
                   .animate()
                   // .scaleX(0.80f)
                   .scaleY(0.80f)
                   .setInterpolator(OvershootInterpolator()).setDuration(300)
                   .start()*/

        fl_image
            .animate()
            //     .scaleX(0.80f)
            .scaleY(1f)
            .setInterpolator(OvershootInterpolator(1.5f))
            .setDuration(300)
            .start()

        textView3
            .animate()
            .translationY(15f)
            .alpha(0f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(300)
            .start()


        textView3.visibility = View.GONE
    }
}
