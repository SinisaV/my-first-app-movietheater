package com.example.movieteather

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.lib.AdvanceTicket
import com.example.movieteather.databinding.ActivityInputDataBinding
import io.github.g00fy2.quickie.ScanQRCode

class InputDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputDataBinding
    //var listOfAdvanceTickets = mutableListOf<AdvanceTicket>()

    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        binding = ActivityInputDataBinding.inflate(layoutInflater)
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
                    val countError: String = getString(R.string.toastCountError)
                    Toast.makeText(applicationContext, countError, Toast.LENGTH_SHORT).show()
                }
                else if (!resultList[0].matches(regex) || !resultList[1].matches(regex) || !resultList[2].matches(regex)) {
                    val numbersError: String = getString(R.string.toastNumbersError)
                    Toast.makeText(applicationContext, numbersError, Toast.LENGTH_SHORT).show()
                }
                else if (resultList[0].contains(".") || resultList[2].contains(".")) {
                    val decimalNumbersError: String = getString(R.string.toastDecimalNumbersError)
                    Toast.makeText(applicationContext, decimalNumbersError, Toast.LENGTH_SHORT).show()
                }
                else {
                    binding.editTextTicketId.setText(resultList[0])
                    binding.editTextTicketPrice.setText(resultList[1])
                    binding.editTextTicketDaysAhead.setText(resultList[2])
                }
            }
        }

        binding.buttonAddTicket.setOnClickListener() {

            if (app.number == 1) {
                if (binding.editTextTicketId.text.isBlank() || binding.editTextTicketPrice.text.isBlank()
                    || binding.editTextTicketDaysAhead.text.isBlank()) {

                    val inputError: String = getString(R.string.toastInputError)
                    val myToast = Toast.makeText(applicationContext, inputError, Toast.LENGTH_SHORT)
                    myToast.setGravity(Gravity.CENTER, 0, 0)
                    myToast.show()
                }
                else {
                    val id: Int = binding.editTextTicketId.text.toString().toInt()
                    val price: Double = binding.editTextTicketPrice.text.toString().toDouble()
                    val daysAhead: Int = binding.editTextTicketDaysAhead.text.toString().toInt()

                    app.data.add(AdvanceTicket(id, price, daysAhead))

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
            else if (app.number == 2) {
                binding.editTextTicketId.setText(app.data[app.position].id)
                binding.editTextTicketPrice.setText(app.data[app.position].price.toString())
                binding.editTextTicketDaysAhead.setText(app.data[app.position].daysAhead)


                val id: Int = binding.editTextTicketId.text.toString().toInt()
                val price: Double = binding.editTextTicketPrice.text.toString().toDouble()
                val daysAhead: Int = binding.editTextTicketDaysAhead.text.toString().toInt()

                app.data[app.position].id = id
                app.data[app.position].price = price
                app.data[app.position].daysAhead = daysAhead

                binding.editTextTicketId.text.clear()
                binding.editTextTicketPrice.text.clear()
                binding.editTextTicketDaysAhead.text.clear()

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
            this.finishAffinity()
            //exitProcess(0)
        }
    }
}