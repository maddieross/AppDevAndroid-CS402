package com.example.finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.objectbox.kotlin.query
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signinButton: Button = findViewById(R.id.signin_button)
        val signupButton: Button = findViewById(R.id.signup_button)
        val userBox = ObjectBox.boxStore.boxFor(User::class.java)
        val emailEditText: EditText = findViewById(R.id.email_edittext)
        val passwordEditText: EditText = findViewById(R.id.pw_edittext)
        val errorTextView: TextView = findViewById(R.id.error_textview)

        signinButton.setOnClickListener{
            try{
                val query= userBox.query {
                    equal(User_.email, emailEditText.text.toString())
                }.property(User_.password).nullValue("unknown")
                val results = query.findString()
               if(results.toString() == passwordEditText.text.toString()){
                    val signin:SignIn = SignIn(emailEditText.text.toString())
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", signin)
                    startActivity(intent)
                }else{
                   errorTextView.text = "password incorrect"
               }
            }catch(t: Throwable){
                errorTextView.text = "account does not exist with that email"
            }


        }

        signupButton.setOnClickListener {
            val intent: Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}
