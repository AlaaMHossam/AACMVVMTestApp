package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.lifetimecode.aacmvvmtestapp.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tiet_notif_email.setOnFocusChangeListener { view, b ->
            if (b) til_notif_email.hint = "Your Name"
            else til_notif_email.hint = "What's your name?"

           // til_notif_email.requestFocus()
        }

      //  til_notif_email.hint = "What's your name?"
    }
}
