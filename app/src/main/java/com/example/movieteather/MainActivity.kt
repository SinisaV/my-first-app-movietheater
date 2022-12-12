package com.example.movieteather

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.icu.text.TimeZoneFormat.TimeType
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.AdvanceTicket
import com.example.movieteather.databinding.ActivityMainBinding
import io.github.serpro69.kfaker.faker
import timber.log.Timber
import java.sql.Time
import kotlin.math.log
import kotlin.random.Random

class MainActivity : AppCompatActivity(), RecyclerViewInterface {

    private lateinit var binding: ActivityMainBinding

    //var listOfAdvanceTickets = mutableListOf<AdvanceTicket>()

    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val faker = faker { }
        //app.data.clear()
        for (i in 1..5) {
            //val id = Random.nextInt(1000, 9999)
            val id = faker.random.nextInt(1000, 9999)
            val price = Random.nextDouble(10.0, 20.0)
            val daysAhead = Random.nextInt(0, 365)
            app.data.add(AdvanceTicket(id, price, daysAhead))

        }
        binding.recyclerView.adapter = MyAdapter(app.data, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        fun onActivityResult(result:ActivityResult) {
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val id: Int = data!!.getIntExtra("id", 1111)
                val price: Double = data.getDoubleExtra("price", 0.0)
                val daysAhead: Int = data.getIntExtra("daysAhead", 0)

                /*app.data.add(
                    AdvanceTicket(id, price, daysAhead)
                )*/
                //binding.textViewResult.text = (app.data.toString())
                binding.recyclerView.adapter = MyAdapter(app.data, this)
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                recreate()
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

            app.number = 1
        }

        binding.buttonAboutActivity.setOnClickListener() {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    /*override fun attachBaseContext(newBase: Context?) {
        LocaleHelper()
        super.attachBaseContext(newBase)
    }*/
    override fun onItemLongCLick(position: Int) {
        val myDialog = AlertDialog.Builder(this)
            .setTitle("Confirm remove item")
            .setMessage("Are you sure, you want this?")
            .setPositiveButton("Yes") { _, _ ->
                app.data.removeAt(position)
                binding.recyclerView.adapter?.notifyItemRemoved(position)
            }
            .setNegativeButton("Cancel") { _, _ ->

            }
            .create()

        myDialog.show()
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(applicationContext, app.data[position].toString(), Toast.LENGTH_SHORT).show()
        app.number = 2
        app.position = position
        val intent = Intent(this, InputDataActivity::class.java)
        startActivity(intent)
    }
}