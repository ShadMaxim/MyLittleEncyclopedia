package com.example.mylittleencyclopedia.presentation.list.example

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.adapter.adapterExample.ExampleListAdapter
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager
import com.yandex.metrica.YandexMetrica

class ExampleListFragment : Fragment(),
    ExampleListAdapter.ClickListener, ExampleViewList {

    private lateinit var adapter: ExampleListAdapter
    private lateinit var prefManager: SharedPrefManager

    private lateinit var idObjectCategory: String

    private var searchText: String = ""
    private var listener: Listener? = null
    private var presenter: ExamplePresenterList? = null
    private var emptyList: MutableList<DataExampleEncyclopedia> = mutableListOf()

    private var progressBar: ProgressBar? = null
    private var frameLayout: FrameLayout? = null

    companion object {
        private const val ID_CATEGORY = "ID_CATEGORY"
        private const val ID_OBJECT = "ID_OBJECT"

        fun getInstance(id_category: String, idObjectCategory: String): ExampleListFragment {
            val fragment = ExampleListFragment()
            val args = Bundle()
            args.putString(ID_CATEGORY, id_category)
            args.putString(ID_OBJECT, idObjectCategory)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view_example, container, false)

        val idCategory = arguments?.getString(ID_CATEGORY)
        idObjectCategory = arguments?.getString(ID_OBJECT).toString()

        presenter = ExamplePresenterList()
        presenter?.setView(this)

        progressBar = view.findViewById(R.id.exampleRecyclerProgressBar)
        frameLayout = view.findViewById(R.id.exampleRecyclerFrameLayout)

        presenter?.firstLoadListExample(searchText, idCategory!!)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewExample)
        recyclerView.setHasFixedSize(true)

        val decor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decor)

        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.isNestedScrollingEnabled = false
        adapter = ExampleListAdapter(load(), this)
        recyclerView.adapter = adapter

        return view
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
        frameLayout?.visibility = View.GONE
    }

    override fun notShowProgressBar() {
        progressBar?.visibility = View.GONE
        frameLayout?.visibility = View.VISIBLE
    }

    override fun showNewList(list: MutableList<DataExampleEncyclopedia>) {
        adapter.updateList(list)
    }

    override fun onResume() {
        super.onResume()
        prefManager = SharedPrefManager(requireContext())
        // dz8SearchEditText.setText(prefManager.readUserName())
        presenter?.reloadRecycler()
    }

    override fun onExampleClick(item: DataExampleEncyclopedia) {
        Toast.makeText(context, item.name + " - это интересно", Toast.LENGTH_SHORT).show()

        val paramsExample = "{\"example\":\"" + item.name + "\"}"
        YandexMetrica.reportEvent("SelectedExample", paramsExample)

        listener?.onExampleClick(item.idObject, idObjectCategory)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        presenter?.detachView()
    }

    private fun load(): MutableList<DataExampleEncyclopedia> {
        return emptyList
    }

    interface Listener {
        fun onExampleClick(id: String, idObjectCategory: String)
    }
}