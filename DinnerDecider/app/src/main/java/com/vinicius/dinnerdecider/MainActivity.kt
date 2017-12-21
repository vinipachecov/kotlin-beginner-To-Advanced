package com.vinicius.dinnerdecider

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.CompletableFuture

class MainActivity : AppCompatActivity() {

    var foodList = arrayListOf("Chinese", "Pizza", "Subway", "Bobs", "Tacos", "Pizza Hut")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedfoodTxt.text = "Mega Burguer"

        decideButton.setOnClickListener{
            val random = Random()
            var randomFood = random.nextInt(foodList.count())
            while(foodList[randomFood] == selectedfoodTxt.text){
                randomFood = random.nextInt(foodList.count())
            }
            selectedfoodTxt.text = foodList[randomFood]
        }

        //simple thread
        addFoodButton.setOnClickListener{
            val newFOod = addFoodTxt.text.toString()
            foodList.add(newFOod)
            addFoodTxt.text.clear()
            Log.d("ddb", "foodlist = ${foodList}")
        }

        CompletableFuture.runAsync(){
            for (i in 1..10){
                println("$i ")
            }
        }
    }
}
