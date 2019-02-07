package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.UserViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.FragmentNotificationsBinding
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentNotificationsBinding: FragmentNotificationsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)

        return fragmentNotificationsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* val userViewModel = UserViewModel()
        userViewModel.emailFocusChangedListener(til_notif_email, tiet_notif_email)
        userViewModel.passwordFocusChangedListener(til_notif_password, tiet_notif_password)*/

/*
        tiet_notif_email.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                til_notif_email.hint = "Your Name"
            else if (!hasFocus && tiet_notif_email.text?.isEmpty()!!)
                til_notif_email.hint = "What's your name?"
        }

        tiet_notif_password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                til_notif_password.hint = "Password"
            else if (!hasFocus && tiet_notif_password.text?.isEmpty()!!)
                til_notif_password.hint = "Add Your Password..."
        }*/
    }

    /*@Bindable
    fun getOnFocusChangeListener(): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { view, isFocussed ->
            Log.d("UserViewModel", "getOnFocusChangeListener : ")

            //    R.id.tiet_notif_email
        }
    }*/
}
