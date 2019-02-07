package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lifetimecode.aacmvvmtestapp.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class UserViewModel : ViewModel() {

    fun emailFocusChangedListener(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
        textInputEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus)
                textInputLayout.hint = "Your Name"
            else if (!hasFocus && textInputEditText.text?.isEmpty()!!)
                textInputLayout.hint = "What's your name?"
        }
    }

    fun passwordFocusChangedListener(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
        textInputEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus)
                textInputLayout.hint = "Password"
            else if (!hasFocus && textInputEditText.text?.isEmpty()!!)
                textInputLayout.hint = "Add Your Password..."
        }
    }

    @Bindable
    fun getOnFocusChangeListener(): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { view, isFocussed ->
            Log.d("UserViewModel", "getOnFocusChangeListener : ")

            //    R.id.tiet_notif_email
        }
    }

}