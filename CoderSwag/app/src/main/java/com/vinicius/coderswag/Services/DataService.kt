package com.vinicius.coderswag.Services

import com.vinicius.coderswag.Model.Category
import com.vinicius.coderswag.Model.Product
import java.util.*

/**
 * Created by vinicius on 12/22/17.
 */

object DataService {

    val categories = listOf(
            Category("SHIRTS", "shirtimage"),
            Category("HOODIES", "hoodieimage"),
            Category("HATS", "hatsimage"),
            Category("DIGITAL", "digitalgoodsimage")
    )
    val hats = listOf(
            Product("Devslopes Graphic Beanie", "$18", "hat01"),
            Product("Devslopes Hat Black", "$20", "hat02"),
            Product("Devslopes Hat White", "$20", "hat03"),
            Product("Devslopes Hat Snapbacl", "$22", "hat04")

    )

    val hoodies = listOf(
            Product("Devslopes Hoodie Gray", "$28", "hoodie01"),
            Product("Hoodie Red", "$30", "hoodie02"),
            Product("Devslopes Gray Hoodie", "$25", "hoodie03"),
            Product("Devslopes Black Hoodie", "$21", "hoodie04")
    )

    val shirts = listOf(
            Product("Devslopes Shirt Gray", "$28", "shirt01"),
            Product("Badge lIght Gray", "$30", "shirt02"),
            Product("Logo Shirt Red", "$25", "shirt03"),
            Product("Devslopes HUstle", "$22", "shirt04"),
                    Product("Kickflip Studios", "$15", "shirt05")
    )
}