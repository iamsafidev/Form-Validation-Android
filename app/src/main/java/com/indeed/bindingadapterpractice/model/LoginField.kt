package com.indeed.bindingadapterpractice.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import com.indeed.bindingadapterpractice.R

class LoginField: BaseObservable() {
    private var email: String? = null
    private var password: String? = null
    var emailError = ObservableField<Int?>()
    var passwordError = ObservableField<Int?>()

    @Bindable
    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
        notifyPropertyChanged(BR.valid)
    }

    @Bindable
    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
        notifyPropertyChanged(BR.valid)
    }

    @Bindable
    fun isValid(): Boolean {
        var valid = isEmailValid(false)
        valid = isPasswordValid(false) && valid
        return valid
    }

    fun isEmailValid(setMessage: Boolean): Boolean {
        // Minimum a@b.c
        if (email != null && email!!.length > 5) {
            val indexOfAt = email!!.indexOf("@")
            val indexOfDot = email!!.lastIndexOf(".")
            return if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email!!.length - 1) {
                emailError.set(null)
                true
            } else {
                if (setMessage) emailError.set(R.string.error_format_invalid)
                false
            }
        }
        if (setMessage) emailError.set(R.string.error_too_short)
        return false
    }

    fun isPasswordValid(setMessage: Boolean): Boolean {
        return if (password != null && password!!.length > 5) {
            passwordError.set(null)
            true
        } else {
            if (setMessage) passwordError.set(R.string.error_too_short)
            false
        }
    }
}