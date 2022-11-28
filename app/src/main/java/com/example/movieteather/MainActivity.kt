package com.example.movieteather

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lib.AdvanceTicket
import com.example.movieteather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var listOfAdvanceTickets = mutableListOf<AdvanceTicket>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fun onActivityResult(result:ActivityResult) {
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val id: Int = data!!.getIntExtra("id", 1111)
                val price: Double = data.getDoubleExtra("price", 0.0)
                val daysAhead: Int = data.getIntExtra("daysAhead", 0)

                listOfAdvanceTickets.add(
                    AdvanceTicket(id, price, daysAhead)
                )
                binding.textViewResult.text = (listOfAdvanceTickets.toString())
            }
        }

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onActivityResult(result)
        }

        binding.buttonInputDataActivity.setOnClickListener() {
            val intent = Intent(this, InputDataActivity::class.java)
            getContent.launch(intent)
        }

        binding.buttonAboutActivity.setOnClickListener() {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}