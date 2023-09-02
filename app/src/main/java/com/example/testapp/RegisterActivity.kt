package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.example.testapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(),
    View.OnClickListener,
    View.OnFocusChangeListener,
    View.OnKeyListener
{

    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
    }

    private fun validateFullName(): Boolean
    {
        var error: String? = null
        val value: String = mBinding.firstname.toString()
        if (value.isEmpty()) {
            error = "Full name is required"
        }

        return error == null
    }

    private fun validateEmail(): Boolean
    {
        var error: String? = null
        val value: String = mBinding.email.toString()
        if (value.isEmpty()) {
            error = "Email is required"
        }

        return error == null
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        TODO("Not yet implemented")
    }
}