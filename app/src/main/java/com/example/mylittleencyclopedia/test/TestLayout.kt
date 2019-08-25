/*
package com.example.mylittleencyclopedia.test

import android.app.Activity
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.mylittleencyclopedia.R

class TestLayout : Activity() {

    var text1: String = "ASDFGHJ"
    var text2: String = "ASDFGHJ"
    var text3: String = "ASDFGHJ"

    var text4: String = "2121 1213123 3123123 123 123 123 123 123 123 12 312 312 3 123 12 31 23 123 12 3 123 12 31 23 123 12"
    var text5: String = "2121 1213123 3123123 123 123 123 123 123 123 12 312 312 3 123 12 31 23 123 12 3 123 12 31 23 123 12"
    var text6: String = "2121 1213123 3123123 123 123 123 123 123 123 12 312 312 3 123 12 31 23 123 12 3 123 12 31 23 123 12"


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)

        createXmlElement(text1, text4)
        createXmlElement(text2, text5)
        createXmlElement(text3, text6)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun createXmlElement(title: String, description: String) {
        val parent = LinearLayout(this)
        parent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        parent.orientation = LinearLayout.VERTICAL

        // TextView1

        val lptv1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lptv1.setMargins(5, 50, 5, 0)

        val lptv2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lptv2.setMargins(5, 5, 5, 0)

        val tv1 = TextView(this)
        tv1.setLayoutParams(lptv1)
        tv1.setText(title) // title
        tv1.background = getDrawable(R.color.fon_example)
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25F)
        tv1.setTypeface(null, Typeface.BOLD)

        val tv2 = TextView(this)
        tv2.setLayoutParams(lptv2)
        tv2.setText(description) // title
        tv2.background = getDrawable(R.color.colorAccent)
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
        tv2.setTypeface(null, Typeface.BOLD)

        parent.removeAllViews()
        parent.addView(tv1)
        parent.addView(tv2)

        val finalParent = this.findViewById(R.id.linearLayoutTest) as ViewGroup
        finalParent.addView(parent)
    }
}*/
