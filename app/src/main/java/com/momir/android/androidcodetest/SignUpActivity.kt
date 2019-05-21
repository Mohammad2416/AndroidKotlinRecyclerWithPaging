package com.momir.android.androidcodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.momir.android.androidcodetest.dataBase.DatabaseHandler
import com.momir.android.androidcodetest.dataModel.Authentication
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val databaseHandler: DatabaseHandler = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        signUpRegisterBtn.setOnClickListener {
            val username: String = signUpTextFieldUsername.text.toString()
            val password: String = signUpTextFieldPassword.text.toString()

            when {
                username.isEmpty() -> signUpTextFieldUsername.error = "Username could not be empty"
                password.isEmpty() -> signUpTextFieldPassword.error = "Password could not be empty"
                else -> addUser(username, password)
            }
        }

    }

    private fun addUser(username: String, password: String) {
        val status = databaseHandler.addUser(Authentication(username, password))
        if (status > -1) {
            Toast.makeText(applicationContext, "record save", Toast.LENGTH_LONG).show()
            finish()
        }

    }
}
