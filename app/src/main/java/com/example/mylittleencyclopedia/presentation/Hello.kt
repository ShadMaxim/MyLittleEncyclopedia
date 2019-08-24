package com.example.mylittleencyclopedia.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_hello.*

class Hello : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        val textName: EditText = findViewById<EditText>(R.id.editTextHello) as EditText
        val prefManager: SharedPrefManager = SharedPrefManager(this)

        buttonHello.setOnClickListener {
            val intent = Intent(this, ManagerActivity::class.java)
            prefManager.saveSharedPrefs(textName.text.toString())
            Toast.makeText(this, "Спасибо, " + textName.text + " , что зашли к нам", Toast.LENGTH_LONG).show()

            val paramsUserName: String = "{\"name\":\"" + textName.text + "\"}"
            YandexMetrica.reportEvent("NewUser", paramsUserName)

            startActivity(intent)

            Log.e("AAA", prefManager.readUserText())

            textName.setText(prefManager.readUserText())
        }
    }
}
