package com.example.mylittleencyclopedia.presentation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.presentation.details.DetailsFragment
import com.example.mylittleencyclopedia.presentation.list.caregory.CategoryListFragment
import com.example.mylittleencyclopedia.presentation.list.example.ExampleListFragment
import com.example.mylittleencyclopedia.presentation.start.StartFragment
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.push.YandexMetricaPush

class ManagerActivity : FragmentActivity(), StartFragment.Listener,
    CategoryListFragment.Listener, ExampleListFragment.Listener,
    MyListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_fragments)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8ContainerHead, StartFragment())
            transaction.commit()
        } else {

            YandexMetrica.reportAppOpen(this)
        }

        val myIntent = intent
        handlePayload(myIntent)
    }

    override fun startUserClick() {
        replaceFragment(R.id.dz8ContainerHead, CategoryListFragment())
    }

    override fun onExampleClick(id: String, idOblectCategory: String) {
        Log.e("AAA onExampleClick =  ", id + "idObCat " + idOblectCategory)
        replaceFragment(R.id.dz8ContainerHead, DetailsFragment.getInstance(id, idOblectCategory))
    }

    override fun onCategoryClick(idCategory: String, idObjectCategory: String) {
        Log.e("AAA onExampleClick =  ", idCategory + "idObCat " + idObjectCategory)
        replaceFragment(R.id.dz8ContainerHead, ExampleListFragment.getInstance(idCategory, idObjectCategory))
    }

    private fun replaceFragment(idContainer: Int, nameFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, nameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRealization() {
        supportFragmentManager.popBackStack()
        if (true) {
            (supportFragmentManager.findFragmentById(R.id.dz8ContainerHead) as CategoryListFragment).updateRecyclerList()
        } else {
            replaceFragment(R.id.dz8ContainerHead, CategoryListFragment())
        }
    }

    override fun onIfNullToBack() {
        supportFragmentManager.popBackStack()
    }

    private fun handlePayload(intent: Intent) {

        val containsIntent = findViewById<TextView>(R.id.testTextView)

        val payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD)
        if (!TextUtils.isEmpty(payload)) {
            containsIntent?.append(String.format("\nPayload: %s", payload))
            YandexMetrica.reportEvent("loading due Push", payload)
        }
    }
}