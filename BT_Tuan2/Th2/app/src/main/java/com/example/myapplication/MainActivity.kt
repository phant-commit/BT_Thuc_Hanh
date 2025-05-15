package com.example.myapplication
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val checkButton = findViewById<Button>(R.id.checkButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        checkButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            when {
                email.isEmpty() -> {
                    resultTextView.text = "Email không hợp lệ"
                }
                !email.contains("@") -> {
                    resultTextView.text = "Email không đúng định dạng"
                }
                else -> {
                    resultTextView.text = "Bạn đã nhập email hợp lệ"
                    resultTextView.setTextColor(resources.getColor(android.R.color.holo_green_dark,null))
                }
            }
        }
    }
}