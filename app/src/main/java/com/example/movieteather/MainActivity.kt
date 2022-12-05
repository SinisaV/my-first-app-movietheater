package com.example.movieteather

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.icu.text.TimeZoneFormat.TimeType
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lib.AdvanceTicket
import com.example.movieteather.databinding.ActivityMainBinding
import timber.log.Timber
import java.sql.Time
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //var listOfAdvanceTickets = mutableListOf<AdvanceTicket>()

    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fun onActivityResult(result:ActivityResult) {
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val id: Int = data!!.getIntExtra("id", 1111)
                val price: Double = data.getDoubleExtra("price", 0.0)
                val daysAhead: Int = data.getIntExtra("daysAhead", 0)

                /*app.data.add(
                    AdvanceTicket(id, price, daysAhead)
                )*/
                binding.textViewResult.text = (app.data.toString())
            }
        }

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onActivityResult(result)
        }

        binding.buttonSettings.setOnClickListener() {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
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