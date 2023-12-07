package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.R.*
import com.example.myapplication.model.DentistModel
import com.example.myapplication.model.TransactionModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var transactionClientInput: EditText
    private lateinit var transactionCaseInput: EditText
    private lateinit var transactionPriceInput: EditText
    private lateinit var submitTransactionForm: Button
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dentistListButton = findViewById<LinearLayout>(id.dentist_list_btn)
        val addDentistButton = findViewById<LinearLayout>(id.add_dentist_btn)
        val transactionsButton = findViewById<LinearLayout>(id.transactions_btn)
        val profileButton = findViewById<ImageButton>(id.profile_btn)
        val notificationButton = findViewById<ImageButton>(id.notification_btn)

        transactionClientInput = findViewById(R.id.transactionClientInput)
        transactionCaseInput = findViewById(R.id.transactionCaseInput)
        transactionPriceInput = findViewById(R.id.transactionPriceInput)
        submitTransactionForm = findViewById(R.id.add_transaction_btn)

        dbRef = FirebaseDatabase.getInstance().getReference("Transaction")

        submitTransactionForm.setOnClickListener {
            submitTransactionData()
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
            val intent = Intent(this, Transactions::class.java)
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
    private fun submitTransactionData() {

        //getting values
        val transactionClient = transactionClientInput.text.toString()
        val transactionCase = transactionCaseInput.text.toString()
        val transactionPrice = transactionPriceInput.text.toString()

        if (transactionClientInput.text.isEmpty()) {
            transactionClientInput.error = "Please enter name"
        }
        if (transactionCaseInput.text.isEmpty()) {
            transactionCaseInput.error = "Please enter the case"
        }
        if (transactionPriceInput.text.isEmpty()) {
            transactionPriceInput.error = "Please enter the price"
        }

        val transactionID = dbRef.push().key!!

        val transaction = TransactionModel(transactionID, transactionClient, transactionCase, transactionPrice)

        dbRef.child(transactionID).setValue(transaction)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                transactionClientInput.text.clear()
                transactionCaseInput.text.clear()
                transactionPriceInput.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}