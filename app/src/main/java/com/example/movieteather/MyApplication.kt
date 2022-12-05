package com.example.movieteather
import org.apache.commons.io.FileUtils
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.lib.AdvanceTicket
import com.google.gson.Gson
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.*

const val MY_FILE_NAME = "myData.json"
const val MY_SP_FILE_NAME = "myshared.data"

class MyApplication : Application() {
    lateinit var data:MutableList<AdvanceTicket>
    lateinit var sharedPref: SharedPreferences
    lateinit var id:String
    private lateinit var gson: Gson
    private lateinit var file: File

    override fun onCreate() {
        super.onCreate()
        initShared()
        if (!containsID()) {
            saveID(UUID.randomUUID().toString().replace("-", ""))
        }
        Timber.d("ID of app is ${getID()}")

        if (BuildConfig.DEBUG)
        {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("On create application")

        data = mutableListOf()
        gson = Gson()
        file = File(filesDir, MY_FILE_NAME)
        Timber.d("File name path ${file.path}")
    }

    fun initShared() {
        sharedPref = getSharedPreferences(MY_SP_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveID(id:String) {
        with(sharedPref.edit()) {
            putString("ID", id)
            apply()
        }
    }

    fun containsID():Boolean {
        return sharedPref.contains("ID")
    }

    fun getID():String? {
        return sharedPref.getString("ID", "DefaultNoData")
    }

    fun saveToFile() {
        try {
            FileUtils.writeStringToFile(file, gson.toJson(data))
            Timber.d("Save to file.")

        }
        catch (e: IOException) {
            Timber.d("Can't save to file: ${file.path}")
        }
    }

    fun deleteFile() {
        try {
            FileUtils.deleteDirectory(file)
        }
        catch (e: IOException) {
            Timber.d("Can't delete file: ${file.path}")
        }
    }

    fun initData() {
        try {
            Timber.d("My file data:${FileUtils.readFileToString(file)}")
            gson.fromJson(FileUtils.readFileToString(file), AdvanceTicket::class.java)
        }
        catch (e: IOException) {
            Timber.d("No file init data.")
        }
    }
}