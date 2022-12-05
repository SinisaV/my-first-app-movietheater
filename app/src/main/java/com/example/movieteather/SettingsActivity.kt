package com.example.movieteather

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieteather.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    lateinit var sharedPref: SharedPreferences
    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences(MY_SP_FILE_NAME, Context.MODE_PRIVATE)
        app = application as MyApplication

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var theme: String
        var language: String

        binding.buttonLightTheme.setOnClickListener() {
            theme = "l"
            saveTheme(theme)
        }

        binding.buttonDarkTheme.setOnClickListener() {
            theme = "d"
            saveTheme(theme)
        }

        binding.buttonEnLanguage.setOnClickListener() {
            language = "en"
            saveLanguage(language)
        }
        binding.buttonDeLanguage.setOnClickListener() {
            language = "de"
            saveLanguage(language)
        }
        binding.buttonSlLanguage.setOnClickListener() {
            language = "sl"
            saveLanguage(language)
        }
    }

    private fun saveTheme(theme: String) {
        with(sharedPref.edit()) {
            when (theme) {
                "l" -> {
                    putString("theme", "Light theme")
                    apply()
                }
                "d" -> {
                    putString("theme", "Dark theme")
                    apply()
                }
                else -> {
                    putString("theme", "Light theme")
                    apply()
                }
            }
        }
    }

    private fun saveLanguage(language: String) {
        with(sharedPref.edit()) {
            when (language) {
                "en" -> {
                    putString("language", "english")
                    apply()
                }
                "de" -> {
                    putString("language", "deutsch")
                    apply()
                }
                "sl" -> {
                    putString("language", "slovenian")
                    apply()
                }
                else -> {
                    putString("language", "english")
                    apply()
                }
            }
        }
    }
}