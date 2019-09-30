package com.nabil.shareapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_log_in_page.*

class signUpPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_page)

        val sp = this.getSharedPreferences("com.nabil.shareapp", Context.MODE_PRIVATE)
        signUpButton.setOnClickListener {
            val isSignedUp = sp.getBoolean("isSignedUp", false)

            println(isSignedUp)

            when (isSignedUp) {
                false -> {
                    sp.edit().putString("usernameValue", usernameInput.text.toString()).apply()
                    sp.edit().putString("passwordValue", passwordInput.text.toString()).apply()
                    sp.edit().putBoolean("isSignedUp", true).apply()

                    Toast.makeText(applicationContext, "Signed Up!", Toast.LENGTH_SHORT).show()
                    goToLoginPage()

                }

                true -> {
                    Toast.makeText(applicationContext, "Already Signed Up!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun goToLoginPage() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}
