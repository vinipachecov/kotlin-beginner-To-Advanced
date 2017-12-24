package com.vinicius.coderswag.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.vinicius.coderswag.Adapters.CategoryAdapter
import com.vinicius.coderswag.Adapters.CategoryRecycleAdapter
import com.vinicius.coderswag.Model.Category
import com.vinicius.coderswag.R
import com.vinicius.coderswag.Services.DataService
import com.vinicius.coderswag.Utilities.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CategoryRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        using lambdas as a parameter
        adapter = CategoryRecycleAdapter(this, DataService.categories) {
            //            code to happen when someone click on the recyclerview
            category ->
            val productIntent = Intent(this, ProductsActivity::class.java)
            productIntent.putExtra(EXTRA_CATEGORY,category.title)
            startActivity(productIntent)
        }

        categoryListView.adapter = adapter

//          Traditional way of clicking things on listview
//        categoryListView.setOnItemClickListener { adapterView, view, i, l ->
//            val category = DataService.categories[i]
//            Toast.makeText(this, "YOu clicked on ${category.title} cell" , Toast.LENGTH_SHORT)
//
//        }

//        create a layout manager to handle recycler view

        val layoutManager = LinearLayoutManager(this)
        categoryListView.layoutManager = layoutManager
        categoryListView.setHasFixedSize(true)


    }
}
