package com.nabil.shareapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //    SIGN IN PAGE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = this.getSharedPreferences("com.nabil.shareapp", Context.MODE_PRIVATE)
        val usernameValue = sp.getString("usernameValue", "")
        val passwordValue = sp.getString("passwordValue", "")

        signInButton.setOnClickListener {

            //            Function below return true if the username and password matches
            if (checkUsernameAndPassword(
                    usernameInput.text.toString(),
                    passwordInput.text.toString(),
                    usernameValue,
                    passwordValue
                )
            ) {

//                Signed In
                Toast.makeText(
                    applicationContext,
                    "Welcome back $usernameValue!",
                    Toast.LENGTH_SHORT
                ).show()

                //                Go to mainPage
                goToMainPage()
            } else {
                Toast.makeText(applicationContext, "Wrong Username/Password!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        signUpButton.setOnClickListener {
            goToSignUpPage()
        }

        signUpButton.setOnLongClickListener {
            clearCredentials()
            true
        }
    }

    fun clearCredentials(){
        val sp = this.getSharedPreferences("com.nabil.shareapp", Context.MODE_PRIVATE)
        sp.edit().clear().apply()

        Toast.makeText(applicationContext, "Cleared Credentials!", Toast.LENGTH_SHORT).show()
    }

    fun goToMainPage(){
        val i = Intent(applicationContext, mainPage::class.java)

        val sp = this.getSharedPreferences("com.nabil.shareapp", Context.MODE_PRIVATE)
        val usernameValue = sp.getString("usernameValue", "")

        i.putExtra("usernameValue", usernameValue)
        startActivity(i)
    }

    fun goToSignUpPage() {
        startActivity(Intent(applicationContext, signUpPage::class.java))
    }

    fun checkUsernameAndPassword(
        uInput: String,
        pInput: String,
        uValue: String?,
        pValue: String?
    ): Boolean {
        if(uInput == "" || pInput == "") return false else {
            return (uInput == uValue && pInput == pValue)
        }
    }

}