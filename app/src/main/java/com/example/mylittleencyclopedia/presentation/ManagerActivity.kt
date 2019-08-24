package com.example.mylittleencyclopedia.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.presentation.details.DetailsFragment
import com.example.mylittleencyclopedia.presentation.list.caregory.CategoryListFragment
import com.example.mylittleencyclopedia.presentation.list.example.ExampleListFragment

class ManagerActivity : FragmentActivity(),
    CategoryListFragment.Listener, ExampleListFragment.Listener,
    MyListener {

    private var isLandOrientation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_fragments)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8ContainerHead, CategoryListFragment())
            transaction.commit()
        }
    }

    override fun onExampleClick(id: String) {
        Log.e("AAA onExampleClick =  ", id)
        replaceFragment(getLandOrPortrait(), DetailsFragment.getInstance(id))
    }

    override fun onCategoryClick(id: String) {
        Log.e("AAA onExampleClick =  ", id)
        replaceFragment(getLandOrPortrait(), ExampleListFragment.getInstance(id))
    }

    private fun replaceFragment(idContainer: Int, nameFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, nameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRealization() {
        supportFragmentManager.popBackStack()
        if (isLandOrientation) {
            (supportFragmentManager.findFragmentById(R.id.dz8ContainerHead) as CategoryListFragment).updateRecyclerList()
        } else {
            replaceFragment(R.id.dz8ContainerHead, CategoryListFragment())
        }
    }

    override fun onEdit(id: String) {
        if (isLandOrientation) {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onIfNullToBack() {
        supportFragmentManager.popBackStack()
    }

    private fun getLandOrPortrait(): Int {
        return if (isLandOrientation) {
            R.id.dz8ContainerSecond
        } else {
            R.id.dz8ContainerHead
        }
    }

    /*override fun onBackPressed() {

        if (isLandOrientation) {

        } else {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8ContainerHead)

            if (fragment is CategoryListFragment) {

                val intent = Intent(this, ManagerActivity::class.java)
                startActivity(intent)
            }
        }

        super.onBackPressed()

    }*/
}