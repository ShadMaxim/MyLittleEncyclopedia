package com.example.mylittleencyclopedia.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.mylittleencyclopedia.R
import java.text.SimpleDateFormat
import java.util.*

class Bay : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bay)

        getTimeStamp(1566723202252)
        Log.e("AAA", getTimeStamp(1566723202252))

    }

    private fun getTimeStamp(timeInMillies: Long): String {
        var date: String? = null
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd") // modify format
        date = formatter.format(Date(timeInMillies))
        System.out.println("Today is " + date)

        return date
    }
}