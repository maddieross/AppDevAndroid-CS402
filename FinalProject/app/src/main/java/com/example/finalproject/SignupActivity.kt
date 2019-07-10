package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.finalproject.User_.email
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder

import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setSupportActionBar(toolbar)

        val houseCodeEditText: EditText = findViewById(R.id.house_code_edittext)
        val emailEditText: EditText = findViewById(R.id.email_edittext)
        val displayNameEditText: EditText = findViewById(R.id.name_edittext)
        val pwEditText: EditText = findViewById(R.id.pw_edittext)
        val repwEditText: EditText = findViewById(R.id.repw_edittext)
        val errorTextView: TextView = findViewById(R.id.error_textview)
        val signupButton: Button = findViewById(R.id.signup_button)
        val newCodeButton: Button = findViewById(R.id.new_code_button);
        val userBox = ObjectBox.boxStore.boxFor(User::class.java)

        val houseCodeBox =ObjectBox.boxStore.boxFor(HouseCode::class.java)


        newCodeButton.setOnClickListener(){

            val code = HouseCode(0)
            houseCodeBox.put(code)
            errorTextView.text= "New House Code: "+code.id
        }



        signupButton.setOnClickListener() {
            errorTextView.text = ""
            try {
                val query = userBox.query {
                    equal(email, emailEditText.text.toString())
                }
                val results = query.find()

                if (results.isNotEmpty()) {
                    errorTextView.text = "account already exist with that email"
                } else if (pwEditText.text.toString() != repwEditText.text.toString()) {
                    errorTextView.text = "passwords do not match"

                } else {
                    val user = User(
                        0,
                        Integer.parseInt(houseCodeEditText.text.toString()),
                        emailEditText.text.toString(),
                        displayNameEditText.text.toString(),
                        pwEditText.text.toString()
                    )
                    userBox.put(user)
                    val signin: SignIn = SignIn(emailEditText.text.toString())
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", signin)
                    startActivity(intent)
                }
            }catch(t: Throwable){
                errorTextView.text = "An error has occurred please try again"
            }



        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


    }

}
