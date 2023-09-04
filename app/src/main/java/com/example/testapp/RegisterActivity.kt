package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.api.RetrofitClient
import com.example.testapp.databinding.ActivityRegisterBinding
import com.example.testapp.entities.User
import com.example.testapp.validator.EmailValidator
import com.example.testapp.validator.EmptyValidator
import com.example.testapp.validator.PasswordValidator
import com.example.testapp.validator.base.BaseValidator
import com.example.testapp.validator.base.ValidateResult
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                getData()
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

    private fun getData()
    {
        val apiService = RetrofitClient.instance

        apiService.getIndex().enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val body = response.body()?.string()
                    val jsonResponse = body?.let { JSONObject(it) }

                    if (jsonResponse != null) {
                        val u: User = User(
                            jsonResponse.getInt("id"),
                            jsonResponse.get("username").toString(),
                            jsonResponse.get("email").toString()
                        )

                        val intent = Intent(this@RegisterActivity, HomePage::class.java)
                        intent.putExtra("user", u.toString())
                        startActivity(intent)
                    }
                } else {
                    val code : Int = response.code()
                    println(code)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun isValidForm(username: ValidateResult, email: ValidateResult, password: ValidateResult): Boolean
    {
        return true
        //return username.isSuccess && email.isSuccess && password.isSuccess;
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