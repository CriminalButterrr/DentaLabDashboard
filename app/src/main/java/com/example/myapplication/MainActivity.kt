package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.myapplication.R.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dentistListButton = findViewById<LinearLayout>(id.dentist_list_btn)
        val addDentistButton = findViewById<LinearLayout>(id.add_dentist_btn)
        val transactionsButton = findViewById<LinearLayout>(id.transactions_btn)
        val addTransactionButton = findViewById<Button>(id.add_transaction_btn)
        val profileButton = findViewById<ImageButton>(id.profile_btn)
        val notificationButton = findViewById<ImageButton>(id.notification_btn)

        addTransactionButton.setOnClickListener {
            val intent = Intent(this, AddTransaction::class.java)
            startActivity(intent)
        }

        dentistListButton.setOnClickListener{
            val intent = Intent(this, DentistList::class.java)
            startActivity(intent)
        }

        addDentistButton.setOnClickListener {
            val intent = Intent(this, AddDentist::class.java)
            startActivity(intent)
        }

        transactionsButton.setOnClickListener {
            val intent = Intent(this,Transactions::class.java)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        notificationButton.setOnClickListener {
            val intent = Intent(this, Notification::class.java)
            startActivity(intent)
        }
    }
}