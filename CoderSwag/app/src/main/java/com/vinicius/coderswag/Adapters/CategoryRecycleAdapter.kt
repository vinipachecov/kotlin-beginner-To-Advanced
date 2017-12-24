package com.vinicius.coderswag.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vinicius.coderswag.Model.Category
import com.vinicius.coderswag.R

/**
 * Created by vinicius on 12/24/17.
 */

class CategoryRecycleAdapter(val context: Context, val categories: List<Category>) : RecyclerView.Adapter<CategoryRecycleAdapter.Holder>() {


//   *Required Methods

    //    called by the recycler to bind the data to a specified location
    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bindCategory(categories[position], context)
    }


    override fun getItemCount(): Int {
        return categories.count()
    }

//  inflate the view if there wasn't a recycler view already created and ready to use
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {

//    create the view
    val view = LayoutInflater.from(context)
            .inflate(R.layout.category_list_item, parent, false)
//        attach to a our holder class to bind our resources later :)
    return Holder(view)

    }
//    *Required Methods


    //    responsable for the binding
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val categoryImage = itemView?.findViewById<ImageView>(R.id.categoryImage)
        val categoryName = itemView?.findViewById<TextView>(R.id.categoryName)


        //        bind resources function
        fun bindCategory(category: Category, context: Context) {

//          fetch the ID of the resource
            val resourceId = context.resources.getIdentifier(category.image, "drawable", context.packageName)

//            set the values of the resources
            categoryImage?.setImageResource(resourceId)
            categoryName?.text = category.title
        }
    }
}