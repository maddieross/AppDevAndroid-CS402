package com.example.homework1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var firstEditText: EditText
    lateinit var secondEditText: EditText

    lateinit var concatButton:Button
    lateinit var addButton: Button

    lateinit var concatTextView:TextView
    lateinit var addTextView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstEditText = findViewById(R.id.first_box_edittext)
        secondEditText = findViewById(R.id.second_box_edittext)

        concatButton = findViewById(R.id.concatenate_button)
        addButton =  findViewById(R.id.add_button)

        concatTextView = findViewById(R.id.textView_concat)
        addTextView = findViewById(R.id.textView_add)

        concatButton.setOnClickListener {
            val concatOutputString = "${firstEditText.text} ${secondEditText.text}"
            concatTextView.text = concatOutputString
            concatTextView.visibility = View.VISIBLE
        }

        addButton.setOnClickListener {

            val text1 = firstEditText.text.toString()
            val text2 = secondEditText.text.toString()
            try {
                val num = Integer.parseInt(text1)
                val num2 = Integer.parseInt(text2)
                val addOutputString = num + num2
                addTextView.text = addOutputString.toString()
                addTextView.visibility = View.VISIBLE
            } catch (e: NumberFormatException) {
                var addOutputString = "${firstEditText.text} ${secondEditText.text}"
                addTextView.text = addOutputString
                addTextView.visibility = View.VISIBLE
            }
        }
        


    }
}


