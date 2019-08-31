package com.example.mylittleencyclopedia.presentation.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager

class StartFragment : Fragment(), StartView {

    private lateinit var prefManager: SharedPrefManager
    private var textName: EditText? = null

    private var listener: Listener? = null
    private var presenter: StartPresenterList? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_hello, container, false)

        presenter = StartPresenterList()
        presenter?.setView(this)
        prefManager = SharedPrefManager(requireContext())

        textName = view.findViewById<EditText>(R.id.editTextHello)

        view.findViewById<Button>(R.id.buttonHello).setOnClickListener {

            prefManager.saveSharedPrefsUserName(textName?.text.toString())

            presenter?.sendReport(textName?.text.toString())
            listener?.startUserClick()
        }
        return view
    }

    override fun showToastHello(userName: String) {
        Toast.makeText(context, "Мы рады приветствовать тебя, " + userName +
                " , на просторах нашей энциклопедии!", Toast.LENGTH_LONG).show()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun startUserClick()
    }
}