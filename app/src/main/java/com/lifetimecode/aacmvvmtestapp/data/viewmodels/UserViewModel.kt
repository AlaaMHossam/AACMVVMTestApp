package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class UserViewModel
@Inject
constructor() : ViewModel() {

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
}