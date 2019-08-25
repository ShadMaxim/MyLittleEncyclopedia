package com.example.mylittleencyclopedia.presentation.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.presentation.MyListener
import com.example.mylittleencyclopedia.util.picassoLoader

class DetailsFragment : Fragment(), ViewDetails {

    private var mylistener: MyListener? = null
    private var presenter: PresenterDetails? = null

    private lateinit var nameDetails: TextView

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val idExample = arguments?.getString(ID_EXAMPLE)
        idObjectCategory = arguments?.getString(ID_CATEGORY).toString()
        Log.e("AAA idObjectCategory = ", "id = " + idObjectCategory)

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

    override fun showToastOk(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun unShowLikes() {
        likes.visibility = View.INVISIBLE
    }

    override fun onDetach() {
        super.onDetach()
        mylistener = null
        presenter!!.detachView()
    }
}