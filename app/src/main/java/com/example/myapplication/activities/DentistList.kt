package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.DentistAdapter
import com.example.myapplication.model.DentistModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DentistList : AppCompatActivity() {

    private lateinit var dentistRecyclerView : RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var dentistList : ArrayList<DentistModel>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_list)

        dentistRecyclerView = findViewById(R.id.rvDentistList)
        dentistRecyclerView.layoutManager = LinearLayoutManager(this)
        dentistRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        dentistList = arrayListOf<DentistModel>()

        getDentistData()
    }

    private fun getDentistData(){
        dentistRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        val dbRef = FirebaseDatabase.getInstance().getReference("Dentist")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dentistList.clear()
                if (snapshot.exists()){
                    for (dentistSnap in snapshot.children){
                        val dentistData = dentistSnap.getValue(DentistModel::class.java)
                        dentistList.add(dentistData!!)
                    }
                    val mAdapater = DentistAdapter(dentistList)
                    dentistRecyclerView.adapter = mAdapater

                    dentistRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}