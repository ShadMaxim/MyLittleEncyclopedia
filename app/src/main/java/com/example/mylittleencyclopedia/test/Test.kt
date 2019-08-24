/*
package com.example.mylittleencyclopedia.test

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.mylittleencyclopedia.R

class Test : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_item)


        val arr1 = intArrayOf(1, 4, 11, 40, 50)
        val arr2 = intArrayOf(3, 5, 6)

        val arr3 = IntArray(arr1.size + arr2.size)

        Log.e("AAA", arr3.size.toString())
        Log.e("AAA", arr1.size.toString())
        Log.e("AAA", arr2.size.toString())

        var i = 0
        var j = 0
        for (x in 0 until arr3.size) {

            if (i > arr1.size - 1) {
                arr3[x] = arr2[j]
                j++

            } else if (j > arr2.size- 1) {
                arr3[x] = arr1[i]
                i++

            } else if (arr1[i] < arr2[j]) {
                arr3[x] = arr1[i]
                i++

            } else if (arr2[j] < arr1[i]) {
                arr3[x] = arr2[j]
                j++
            }
            Log.e("Finish", arr3[x].toString())
            //println(arr3)
        }
    }

}*/
