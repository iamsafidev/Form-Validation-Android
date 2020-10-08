package com.indeed.bindingadapterpractice

import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indeed.bindingadapterpractice.model.LoginField

class MainActivityViewModel : ViewModel() {
    private var login: LoginField? = null
    private var onFocusEmail: OnFocusChangeListener? = null
    private var onFocusPassword: OnFocusChangeListener? = null
    private val buttonClick: MutableLiveData<LoginField> = MutableLiveData<LoginField>()
    /*fun init() {

    }*/

    init {
        login = LoginField()
        onFocusEmail = OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.length > 0 && !focused) {
                login?.isEmailValid(true)
            }
        }
        onFocusPassword = OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.length > 0 && !focused) {
                login?.isPasswordValid(true)
            }
        }
    }

    fun getLogin(): LoginField? {
        return login
    }

    fun getEmailOnFocusChangeListener(): OnFocusChangeListener? {
        return onFocusEmail
    }

    fun getPasswordOnFocusChangeListener(): OnFocusChangeListener? {
        return onFocusPassword
    }

    fun onButtonClick() {
        if (login?.let { it.isValid() }?:return) {
            buttonClick.setValue(login)
        }
    }

    fun getButtonClick(): MutableLiveData<LoginField> {
        return buttonClick
    }

    companion object{
        @JvmStatic
        @BindingAdapter("error")
        open fun setError(editText: EditText, strOrResId: Any?) {
            if (strOrResId is Int) {
                editText.error = editText.context.getString((strOrResId as Int?)!!)
            } else {
                editText.error = strOrResId as String?
            }
        }

        @JvmStatic
        @BindingAdapter("onFocus")
        fun bindFocusChange(editText: EditText, onFocusChangeListener: OnFocusChangeListener?) {
            if (editText.onFocusChangeListener == null) {
                editText.onFocusChangeListener = onFocusChangeListener
            }
        }

    }

}