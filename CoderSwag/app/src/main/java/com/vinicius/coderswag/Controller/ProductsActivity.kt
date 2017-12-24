package com.vinicius.coderswag.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vinicius.coderswag.R
import com.vinicius.coderswag.Utilities.EXTRA_CATEGORY

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        val categorytype = intent.getStringExtra(EXTRA_CATEGORY)
        println(categorytype)

    }
}
