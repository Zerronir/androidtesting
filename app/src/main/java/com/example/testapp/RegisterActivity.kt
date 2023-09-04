package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.databinding.ActivityRegisterBinding
import com.example.testapp.validator.EmailValidator
import com.example.testapp.validator.EmptyValidator
import com.example.testapp.validator.PasswordValidator
import com.example.testapp.validator.base.BaseValidator
import com.example.testapp.validator.base.ValidateResult

class RegisterActivity : AppCompatActivity(),
    View.OnClickListener,
    View.OnFocusChangeListener,
    View.OnKeyListener
{

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val username = binding.inputEditTextUsername.text.toString()
            val email = binding.inputEditTextEmail.text.toString()
            val password = binding.inputEditTextPassword.text.toString()

            val usernameEmptyValidation = EmptyValidator(username).validate()
            binding.inputLayoutUsername.error =
                if (!usernameEmptyValidation.isSuccess)
                    getString(usernameEmptyValidation.message) else null

            val emailValidations = BaseValidator.validate(
                EmptyValidator(email), EmailValidator(email)
            )

            binding.inputLayoutEmail.error =
                if (!emailValidations.isSuccess) getString(emailValidations.message) else null


            val passwordValidations = BaseValidator.validate(
                EmptyValidator(password), PasswordValidator(password)
            )
            binding.inputLayoutPassword.error =
                if (!passwordValidations.isSuccess) getString(passwordValidations.message) else null

            val isValid = isValidForm(usernameEmptyValidation, emailValidations, passwordValidations)
            if (isValid) {
                val intent = Intent(this@RegisterActivity, HomePage::class.java)
                startActivity(intent)
            } else {
                val builder: AlertDialog.Builder = this@RegisterActivity.let {
                    AlertDialog.Builder(it)
                }

                builder
                    .setMessage("Revisa los campos por favor")
                    .setTitle("Error en el registro")

                val dialog: AlertDialog = builder.create()

                dialog.show()
            }
        }

    }

    private fun isValidForm(username: ValidateResult, email: ValidateResult, password: ValidateResult): Boolean
    {
        return username.isSuccess && email.isSuccess && password.isSuccess;
    }

    override fun onClick(view: View?) {
        TODO("todo")
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        TODO("todo")
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}