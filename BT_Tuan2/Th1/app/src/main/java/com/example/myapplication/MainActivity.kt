package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val ageEditText = findViewById<EditText>(R.id.ageEditText)
        val checkButton = findViewById<Button>(R.id.checkButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        checkButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val ageText = ageEditText.text.toString().trim()

            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val age = ageText.toInt()
                val ageGroup = when {
                    age > 65 -> "Người già"
                    age >= 6 -> "Người lớn"
                    age >= 2 -> "Trẻ em"
                    else -> "Em bé"
                }

                resultTextView.text = "Kết quả: $name - $ageGroup"
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Tuổi phải là số", Toast.LENGTH_SHORT).show()
            }
        }
    }
}