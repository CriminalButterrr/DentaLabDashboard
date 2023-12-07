package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.DentistModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDentist : AppCompatActivity() {

    private lateinit var dentistNameInput: EditText
    private lateinit var dentistAddressInput: EditText
    private lateinit var dentistContactInput: EditText
    private lateinit var submitDentistForm: Button

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dentist)

        dentistNameInput = findViewById(R.id.dentistNameInput)
        dentistAddressInput = findViewById(R.id.dentistAddressInput)
        dentistContactInput = findViewById(R.id.dentistContactInput)
        submitDentistForm = findViewById(R.id.add_dentist_btn)

        dbRef = FirebaseDatabase.getInstance().getReference("Dentist")

        submitDentistForm.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {

        //getting values
        val dentistName = dentistNameInput.text.toString()
        val dentistAddress = dentistAddressInput.text.toString()
        val dentistContact = dentistContactInput.text.toString()

        if (dentistNameInput.text.isEmpty()) {
            dentistNameInput.error = "Please enter name"
        }
        if (dentistAddressInput.text.isEmpty()) {
            dentistAddressInput.error = "Please enter age"
        }
        if (dentistContactInput.text.isEmpty()) {
            dentistContactInput.error = "Please enter salary"
        }

        val userID = dbRef.push().key!!

        val dentist = DentistModel(userID, dentistName, dentistAddress, dentistContact)

        dbRef.child(userID).setValue(dentist)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                dentistNameInput.text.clear()
                dentistAddressInput.text.clear()
                dentistContactInput.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}