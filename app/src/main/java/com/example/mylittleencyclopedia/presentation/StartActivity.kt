package com.example.mylittleencyclopedia.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_hello.*
import android.text.TextUtils
import com.example.mylittleencyclopedia.R
import com.yandex.metrica.push.YandexMetricaPush

class StartActivity : Activity() {

    private var containsIntent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        containsIntent = findViewById(R.id.helloContainsIntent)
        val textName: EditText = findViewById<EditText>(R.id.editTextHello)
        val prefManager = SharedPrefManager(this)

        if (savedInstanceState != null) {
            YandexMetrica.reportAppOpen(this)
        }

        val myIntent = intent
        handlePayload(myIntent)

        buttonHello.setOnClickListener {
            val intent = Intent(this, ManagerActivity::class.java)
            prefManager.saveSharedPrefsUserName(textName.text.toString())
            Toast.makeText(this, "Мы рады приветствовать тебя, " + textName.text +
                    " , на просторах нашей энциклопедии!", Toast.LENGTH_LONG).show()

            val paramsUserName: String = "{\"name\":\"" + textName.text + "\"}"
            YandexMetrica.reportEvent("NewUser", paramsUserName)

            startActivity(intent)

            Log.e("AAA", prefManager.readUserName())

            textName.setText(prefManager.readUserName())
        }
    }

    private fun handlePayload(intent: Intent) {
        val payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD)
        if (!TextUtils.isEmpty(payload)) {
            containsIntent?.append(String.format("\nPayload: %s", payload))
            YandexMetrica.reportEvent("loading due Push", payload)
        }
    }

    override fun onPause() {
        YandexMetrica.pauseSession(this)
        super.onPause()
    }

    override fun onResume() {
        YandexMetrica.resumeSession(this)
        super.onResume()
    }
}
