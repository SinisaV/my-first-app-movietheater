package com.example.movieteather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.example.lib.AdvanceTicket
import com.example.lib.Theatre

import com.example.movieteather.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/

    private lateinit var binding: ActivityMainBinding
    var listOfAdvanceTickets = mutableListOf<AdvanceTicket>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonAddTicket.setOnClickListener() {

            if (binding.editTextTicketId.text.isBlank() || binding.editTextTicketPrice.text.isBlank()
                || binding.editTextTicketDaysAhead.text.isBlank()) {

                val myToast = Toast.makeText(applicationContext, "Enter again!!!", Toast.LENGTH_SHORT);
                myToast.setGravity(Gravity.CENTER, 0, 0)
                myToast.show()
            }
            else {
                val id: Int = binding.editTextTicketId.text.toString().toInt()
                val price: Double = binding.editTextTicketPrice.text.toString().toDouble()
                val daysAhead: Int = binding.editTextTicketDaysAhead.text.toString().toInt()

                listOfAdvanceTickets.add(AdvanceTicket(id, price, daysAhead))

                binding.editTextTicketId.text.clear()
                binding.editTextTicketPrice.text.clear()
                binding.editTextTicketDaysAhead.text.clear()
            }
        }

        binding.buttonInfo.setOnClickListener() {
            Log.i("List size: ", listOfAdvanceTickets.size.toString());
        }
        binding.buttonExit.setOnClickListener() {
            exitProcess(0);
        }
    }
}