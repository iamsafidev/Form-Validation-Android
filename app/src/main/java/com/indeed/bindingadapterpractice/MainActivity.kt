package com.indeed.bindingadapterpractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.indeed.bindingadapterpractice.databinding.ActivityMainBinding
import com.indeed.bindingadapterpractice.model.LoginField

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        activityMainBinding.model= mainActivityViewModel
        if (savedInstanceState == null) {
            mainActivityViewModel.init()
        }
        setupButtonClick()


    }

    private fun setupButtonClick() {
        mainActivityViewModel.getButtonClick().observe(this,
            Observer<LoginField> { loginModel ->
                Toast.makeText(
                    this,
                    "Email " + loginModel.getEmail()
                        .toString() + ", Password " + loginModel.getPassword(),
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}