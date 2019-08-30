package com.example.mylittleencyclopedia.presentation.start

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager
import com.yandex.metrica.YandexMetrica

class StartFragment : Fragment() {

    private lateinit var prefManager: SharedPrefManager

    private var name: String = ""
    private var listener: Listener? = null

    private var containsIntent: TextView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_hello, container, false)

        containsIntent = view.findViewById(R.id.helloContainsIntent)
        val textName: EditText = view.findViewById<EditText>(R.id.editTextHello)
        val prefManager = SharedPrefManager(requireContext())

        if (savedInstanceState != null) {
            YandexMetrica.reportAppOpen("")
        }

        view.findViewById<Button>(R.id.buttonHello).setOnClickListener {

            prefManager.saveSharedPrefsUserName(textName.text.toString())
            Toast.makeText(context, "Мы рады приветствовать тебя, " + textName.text +
                    " , на просторах нашей энциклопедии!", Toast.LENGTH_LONG).show()

            val paramsUserName: String = "{\"name\":\"" + textName.text + "\"}"
            YandexMetrica.reportEvent("NewUser", paramsUserName)

            Log.e("AAA", prefManager.readUserName())

            textName.setText(prefManager.readUserName())

            listener?.startUserClick()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        prefManager = SharedPrefManager(requireContext())
        name = prefManager.readUserName()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun startUserClick()
    }
}