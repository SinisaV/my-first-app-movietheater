package com.example.movieteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.example.lib.AdvanceTicket
import com.example.movieteather.databinding.ActivityMainBinding
import io.github.g00fy2.quickie.ScanQRCode
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var listOfAdvanceTickets = mutableListOf<AdvanceTicket>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) { result ->

            val getResultString: String = result.toString().substringAfter("Value=").substringBefore(")")
            //Toast.makeText(applicationContext, getResultString, Toast.LENGTH_SHORT).show()
            val splitResult = " "
            val resultList = getResultString.split(splitResult)
            val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

            for (i in resultList) {
                if (resultList.count() != 3) {
                    Toast.makeText(applicationContext, "ERROR COUNT", Toast.LENGTH_SHORT).show()
                }
                else if (!resultList[0].matches(regex) || !resultList[1].matches(regex) || !resultList[2].matches(regex)) {
                    Toast.makeText(applicationContext, "ID, price or daysAhead contains only numbers!", Toast.LENGTH_SHORT).show()
                }
                else if (resultList[0].contains(".") || resultList[2].contains(".")) {
                    Toast.makeText(applicationContext, "ID and DaysAhead cant have decimal numbers!", Toast.LENGTH_SHORT).show()
                }
                else {
                    binding.editTextTicketId.setText(resultList[0])
                    binding.editTextTicketPrice.setText(resultList[1])
                    binding.editTextTicketDaysAhead.setText(resultList[2])
                }
            }
        }

        binding.buttonAddTicket.setOnClickListener() {

            if (binding.editTextTicketId.text.isBlank() || binding.editTextTicketPrice.text.isBlank()
                || binding.editTextTicketDaysAhead.text.isBlank()) {

                val myToast = Toast.makeText(applicationContext, "Enter again!!!", Toast.LENGTH_SHORT)
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

                val data = Intent()

                data.putExtra("id", id)
                data.putExtra("price",price)
                data.putExtra("daysAhead", daysAhead)

                setResult(RESULT_OK, data)
                finish()
            }
        }

        /*binding.buttonInfo.setOnClickListener() {
            Log.i("List size: ", listOfAdvanceTickets.size.toString())
        }*/

        binding.buttonScanQRCode.setOnClickListener {
            scanQrCodeLauncher.launch(null)
        }
        binding.buttonExit.setOnClickListener() {
            exitProcess(0)
        }
    }
}