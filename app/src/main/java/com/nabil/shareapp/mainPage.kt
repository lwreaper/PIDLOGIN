package com.nabil.shareapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_page.*

class mainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val usernameValue = intent.extras!!.getString("usernameValue")
        welcomeText.setText("Welcome back $usernameValue!")

        clearCredentialsButton.setOnClickListener {
            clearCredentials()
        }

        showPasswordButton.setOnClickListener {
            showPassword()
        }
    }


    fun showPassword(){
        val sp = this.getSharedPreferences("com.nabil.shareapp", Context.MODE_PRIVATE)
        val passwordValue = sp.getString("passwordValue", "")

        Toast.makeText(applicationContext, "Password: $passwordValue", Toast.LENGTH_SHORT).show()
    }
    fun clearCredentials(){
        val sp = this.getSharedPreferences("com.nabil.shareapp", Context.MODE_PRIVATE)
        sp.edit().clear().apply()

        goToLoginPage()
        Toast.makeText(applicationContext, "Cleared Credentials!", Toast.LENGTH_SHORT).show()
    }

    fun goToLoginPage() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}
