package com.example.mylittleencyclopedia.presentation.list.caregory

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.adapter.adapterCategory.CategoryListAdapter
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.presentation.pageload.AutoLoadRecyclerListener
import com.example.mylittleencyclopedia.presentation.sharedPrefs.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_recycler_view_gategory.view.*

class CategoryListFragment : Fragment(),
    CategoryListAdapter.ClickListener, CategoryViewList {

    private lateinit var adapter: CategoryListAdapter
    private lateinit var prefManager: SharedPrefManager

    private lateinit var searchEditText: EditText

    private var searchText: String = ""
    private var name: String = ""
    private var listener: Listener? = null
    private lateinit var scrollListener: AutoLoadRecyclerListener
    private var presenter: CategoryPresenterList? = null
    private var emptyList: MutableList<DataExampleEncyclopedia> = mutableListOf()

    private var progressBar: ProgressBar? = null
    private var frameLayout: FrameLayout? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view_gategory, container, false)

        presenter = CategoryPresenterList()
        presenter?.setView(this)

        progressBar = view.findViewById(R.id.categoryRecyclerProgressBar)
        frameLayout = view.findViewById(R.id.categoryRecyclerFrameLayout)

        presenter?.loadList(searchText)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCategory)
        recyclerView.setHasFixedSize(true)

        val decor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decor)

        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.isNestedScrollingEnabled = false
        adapter = CategoryListAdapter(load(), this)
        recyclerView.adapter = adapter

        scrollListener = object : AutoLoadRecyclerListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter?.loadMore(page, searchText)
            }
        }
        recyclerView.addOnScrollListener(scrollListener)

        searchEditText = view.searchEditTextCategory
        searchEditText.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer = Handler()
                timer?.postDelayed({
                    searchText = p0.toString()
                    scrollListener.resetPages()
                    presenter?.loadList(searchText)
                }, 500)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        return view
    }

    override fun showNewList(list: MutableList<DataExampleEncyclopedia>) {
        adapter.updateList(list)
    }

    override fun onPause() {
        super.onPause()
        prefManager.saveSharedPrefsSearchNameCategory(searchEditText.text.toString())
    }

    override fun onResume() {
        super.onResume()
        prefManager = SharedPrefManager(requireContext())
        searchEditText.setText(prefManager.readSearchNameCategory())
        presenter?.reloadRecycler()
    }

    override fun onCategoryClick(item: DataExampleEncyclopedia) {
        name = prefManager.readUserName()
        Toast.makeText(context, name + ", на это страничке ты больше узнаешь о категории " + item.category, Toast.LENGTH_SHORT).show()

        presenter?.sendReport(item.category)

        listener?.onCategoryClick(item.id_category, item.idObject)
    }

    fun updateRecyclerList() {
        val list = presenter?.newListForSearch(searchText)
        showNewList(list!!)
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
        frameLayout?.visibility = View.GONE
    }

    override fun notShowProgressBar() {
        progressBar?.visibility = View.GONE
        frameLayout?.visibility = View.VISIBLE
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
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
        fun onCategoryClick(idCategory: String, idObject: String)
    }
}