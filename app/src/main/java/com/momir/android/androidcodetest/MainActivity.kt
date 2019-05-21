package com.momir.android.androidcodetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.momir.android.androidcodetest.dataBase.DatabaseHandler
import com.momir.android.androidcodetest.dataModel.Authentication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val databaseHandler: DatabaseHandler = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginBtn.setOnClickListener {
            val username: String = textFieldUsername.text.toString()
            val password: String = textFieldPassword.text.toString()

            when {
                username.isEmpty() -> textFieldUsername.error = "Username could not be empty"
                password.isEmpty() -> textFieldPassword.error = "Password could not be empty"
                else -> searchUser(username, password)
            }
        }


        registerBtn.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }


    }


    private fun searchUser(username: String, password: String) {
        val user: List<Authentication> = databaseHandler.findAllUser()

        if (user.contains(Authentication(username,password))) {
            Toast.makeText(this, " hey ${user[0].userName}", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ListOfUsersActivity::class.java))

        }else{
            Toast.makeText(this, "Username Or Password is not valid",Toast.LENGTH_LONG).show()
        }

    }


}
