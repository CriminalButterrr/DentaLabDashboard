package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addTransactionButton = findViewById<Button>(id.add_transaction_btn)
        addTransactionButton.setOnClickListener {
            val Intent = Intent(this, add_transaction::class.java)
            startActivity(Intent)
        }

    }
}