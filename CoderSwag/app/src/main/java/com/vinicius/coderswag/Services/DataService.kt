package com.vinicius.coderswag.Services

import com.vinicius.coderswag.Model.Category
import com.vinicius.coderswag.Model.Product
import java.util.*

/**
 * Created by vinicius on 12/22/17.
 */

object DataService {

//    the 'image' string is the name of the image file of the topic
    val categories = listOf(
            Category("SHIRTS", "shirtimage"),
            Category("HOODIES", "hoodieimage"),
            Category("HATS", "hatimage"),
            Category("DIGITAL", "digitalgoodsimage"),
        Category("SHIRTS", "shirtimage"),
        Category("HOODIES", "hoodieimage"),
        Category("HATS", "hatimage"),
        Category("DIGITAL", "digitalgoodsimage"),
        Category("SHIRTS", "shirtimage"),
        Category("HOODIES", "hoodieimage"),
        Category("HATS", "hatimage"),
        Category("DIGITAL", "digitalgoodsimage")
    )

    val hats = listOf(
            Product("Devslopes Graphic Beanie", "$18", "hat1"),
            Product("Devslopes Hat Black", "$20", "hat2"),
            Product("Devslopes Hat White", "$20", "hat3"),
            Product("Devslopes Hat Snapbacl", "$22", "hat4")

    )

    val hoodies = listOf(
            Product("Devslopes Hoodie Gray", "$28", "hoodie1"),
            Product("Hoodie Red", "$30", "hoodie2"),
            Product("Devslopes Gray Hoodie", "$25", "hoodie3"),
            Product("Devslopes Black Hoodie", "$21", "hoodie4")
    )

    val shirts = listOf(
            Product("Devslopes Shirt Gray", "$28", "shirt1"),
            Product("Badge lIght Gray", "$30", "shirt2"),
            Product("Logo Shirt Red", "$25", "shirt3"),
            Product("Devslopes HUstle", "$22", "shirt4"),
                    Product("Kickflip Studios", "$15", "shirt5")
    )

    val digitalGood = listOf<Product>()

    fun getProducts(category: String) : List<Product> {
        return when (category){
            "shirts" -> return shirts
            "hats" -> return hats
            "HOODIES" -> return hoodies
            else -> return digitalGood
        }
    }
}