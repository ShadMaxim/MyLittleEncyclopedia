package com.example.mylittleencyclopedia.presentation.details

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataComments
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.presentation.MyListener
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager
import com.example.mylittleencyclopedia.util.picassoLoader
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), ViewDetails {

    private var mylistener: MyListener? = null
    private var presenter: PresenterDetails? = null
    private lateinit var prefManager: SharedPrefManager

    private lateinit var nameDetails: TextView

    private var editTextComments: EditText? = null
    private var buttonSendComments: Button? = null
    private var buttonShowComments: Button? = null
    private var buttonCloseComments: Button? = null
    private var linearLayoutForComments: LinearLayout? = null

    private lateinit var textOne: TextView
    private lateinit var textTwo: TextView
    private lateinit var text3: TextView
    private lateinit var text4: TextView
    private lateinit var text5: TextView
    private lateinit var text6: TextView
    private lateinit var text7: TextView

    private lateinit var urlHead: ImageView
    private lateinit var url2: ImageView
    private lateinit var url3: ImageView
    private lateinit var url4: ImageView
    private lateinit var url5: ImageView
    private lateinit var url6: ImageView
    private lateinit var url7: ImageView

    private lateinit var likes: ImageView
    private lateinit var idObjectCategory: String

    private var titleExample: String = ""

    companion object {
        private const val ID_EXAMPLE = "ID_EXAMPLE"
        private const val ID_CATEGORY = "ID_CATEGORY"

        fun getInstance(idExample: String, idObjectCategory: String): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putString(ID_EXAMPLE, idExample)
            args.putString(ID_CATEGORY, idObjectCategory)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MyListener)
            mylistener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        retainInstance = true

        val idExample = arguments?.getString(ID_EXAMPLE)
        idObjectCategory = arguments?.getString(ID_CATEGORY).toString()
        Log.e("AAA idObjectCategory = ", "id = " + idObjectCategory)
        prefManager = SharedPrefManager(requireContext())

        editTextComments = view.findViewById(R.id.detailsEdiTextComments)
        buttonSendComments = view.findViewById(R.id.detailsButtonSendComments)
        buttonShowComments = view.findViewById(R.id.detailsButtonShowComments)
        buttonCloseComments = view.findViewById(R.id.detailsButtonCloseComment)
        linearLayoutForComments = view.findViewById(R.id.detailsLinearComments)

        likes = view.findViewById(R.id.detailsLikesImageView)

        nameDetails = view.findViewById(R.id.exampleDetailsNameTextView)

        textOne = view.findViewById(R.id.exampleDetailsTextOneTextView)
        textTwo = view.findViewById(R.id.exampleDetailsTextTwoTextView)
        text3 = view.findViewById(R.id.exampleDetailsText3TextView)
        text4 = view.findViewById(R.id.exampleDetailsText4TextView)
        text5 = view.findViewById(R.id.exampleDetailsText5TextView)
        text6 = view.findViewById(R.id.exampleDetailsText6TextView)
        text7 = view.findViewById(R.id.exampleDetailsText7TextView)

        urlHead = view.findViewById(R.id.exampleDetailsHeadImageView)
        url2 = view.findViewById(R.id.exampleDetails2ImageView)
        url3 = view.findViewById(R.id.exampleDetails3ImageView)
        url4 = view.findViewById(R.id.exampleDetails4ImageView)
        url5 = view.findViewById(R.id.exampleDetails5ImageView)
        url6 = view.findViewById(R.id.exampleDetails6ImageView)
        url7 = view.findViewById(R.id.exampleDetails7ImageView)

        presenter = PresenterDetails()
        presenter!!.setView(this)

        if (idExample != null) {
            presenter!!.getExampleById(idExample, idObjectCategory)
        }

        view.findViewById<ImageView>(R.id.detailsLikesImageView).setOnClickListener {
            presenter!!.clickLikes(idObjectCategory)
            Log.e("AAA clickLikes =  ", "id = " + idObjectCategory)
        }

        view.findViewById<Button>(R.id.detailsButtonShowComments).setOnClickListener {
            presenter!!.showComments()
        }

        view.findViewById<Button>(R.id.detailsButtonSendComments).setOnClickListener {

            val textComments = detailsEdiTextComments.text.toString()
            presenter!!.sendNewComments(textComments, prefManager.readUserName())
        }

        view.findViewById<Button>(R.id.detailsButtonCloseComment).setOnClickListener {
            presenter!!.unShowCloseButtonComments()
        }

        return view
    }

    override fun showExample(example: DataExampleEncyclopedia) {
        picassoLoader(example.url_box_one, urlHead)
        picassoLoader(example.url_box_two, url2)
        picassoLoader(example.url_box_three, url3)
        picassoLoader(example.url_box_four, url4)
        picassoLoader(example.url_box_five, url5)
        picassoLoader(example.url_box_six, url6)
        picassoLoader(example.url_box_seven, url7)

        nameDetails.text = example.name
        textOne.text = example.text_box_one
        textTwo.text = example.text_box_two
        text3.text = example.text_box_three
        text4.text = example.text_box_four
        text5.text = example.text_box_five
        text6.text = example.text_box_six
        text7.text = example.text_box_seven

        titleExample = example.name
    }

    override fun showComments(listComments: List<DataComments>, listData: List<String>) {

        for (i in 0 until listComments.size) {

            val parent = LinearLayout(context)
            parent.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            parent.orientation = LinearLayout.VERTICAL

            val linearLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayoutParams.setMargins(5, 10, 5, 0)

            val linearLayoutParamsForName = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayoutParamsForName.setMargins(5, 15, 5, 0)

            val linearLayoutParamsForDate = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayoutParamsForDate.setMargins(5, 3, 5, 0)

            val textComments = TextView(context)
            textComments.layoutParams = linearLayoutParams
            textComments.text = listComments[i].textComments
            textComments.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
            textComments.setTypeface(null, Typeface.NORMAL)

            val textName = TextView(context)
            textName.layoutParams = linearLayoutParamsForName
            textName.text = listComments[i].userName
            textName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            textName.setTypeface(null, Typeface.BOLD_ITALIC)

            val textDate = TextView(context)
            textDate.layoutParams = linearLayoutParamsForDate
            textDate.text = listData[i]
            textDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
            textDate.setTypeface(null, Typeface.NORMAL)

            val space = TextView(context)
            space.setLayoutParams(linearLayoutParams)
            space.setText("")
            space.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
            space.setTypeface(null, Typeface.NORMAL)

            parent.removeAllViews()
            parent.addView(textName)
            parent.addView(textDate)
            parent.addView(textComments)
            parent.addView(space)

            val finalParent = view!!.findViewById(R.id.detailsLinearComments) as ViewGroup
            finalParent.addView(parent)
        }
    }

    override fun unShowSendComment() {
        editTextComments?.visibility = View.GONE
        buttonSendComments?.visibility = View.GONE
    }

    override fun unShowButtonShowComment() {
        buttonShowComments?.visibility = View.GONE
    }

    override fun showCloseComment() {
        buttonCloseComments?.visibility = View.VISIBLE
    }

    override fun unShowCloseButtonComment() {
        buttonCloseComments?.visibility = View.GONE
        linearLayoutForComments?.visibility = View.GONE
    }

    override fun showToastOk(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun unShowLikes(nameExample: String) {

        val clickLikes = "{\"title\":\"$nameExample\"}"
        YandexMetrica.reportEvent("ClickLikes", clickLikes)

        likes.visibility = View.INVISIBLE
    }

    override fun onDetach() {
        super.onDetach()
        mylistener = null
        presenter!!.detachView()
    }
}