package com.vinicius.smack.Services

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vinicius.smack.Controller.App
import com.vinicius.smack.Utilities.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by vinicius on 12/26/17.
 */

object AuthService {

//    var authToken: String = ""
//    var isLogged = false
//    var userEmail: String = ""

    fun registerUser(email: String, password: String, complete: (Boolean) -> Unit) {


        println("email = $email")
        println("password = $password")
        // create our jsonObject to be sent
        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

//        response and error listener
        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->
            println(response)
            complete(true)
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not register a new user =/. ${error.printStackTrace()}")
            complete(false)
        }) {
            //            header
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            //turn our requestbody to bytearray
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

//        add request to the volley que
        App.sharedPreferences.requestQeue.add(registerRequest)
    }


    fun loginUser( email: String, password: String, complete: (Boolean) -> Unit) {


        // create our jsonObject to be sent
        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener { response ->
            // parse the json data
            println(response)

            try {
                App.sharedPreferences.userEmail = response.getString("user")
                App.sharedPreferences.authToken = response.getString("token")
                App.sharedPreferences.isLogged = true
            } catch (e: JSONException) {
                Log.d("JSON", "EXC: ${e.localizedMessage}")
                complete(false)
            }
            complete(true)
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not login the new user =/. $error")
            complete(false)
        }) {


            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            //turn our requestbody to bytearray
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        App.sharedPreferences.requestQeue.add(loginRequest)

    }


    fun createUser( name: String, email: String, avatarColor: String, avatarName: String, complete: (Boolean) -> Unit) {
        // create our jsonObject to be sent
        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("email", email)
        jsonBody.put("avatarName", avatarName)
        jsonBody.put("avatarColor", avatarColor)
        val requestBody = jsonBody.toString()


        val createRequest = object : JsonObjectRequest(Method.POST, URL_CREATE_USER, null,
                Response.Listener { response ->
                    println(response)

                    try {
                        UserDataService.name = response.getString("name")
                        UserDataService.email = response.getString("email")
                        UserDataService.avatarName = response.getString("avatarName")
                        UserDataService.avatarColor = response.getString("avatarColor")
                        UserDataService.id = response.getString("_id")
                        complete(true)
                    } catch (e: JSONException) {
                        Log.d("ERROR", "Could not create new user =/. ${e.printStackTrace()}")
                        complete(false)
                    }

                }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not create new user =/. $error")
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            //turn our requestbody to bytearray
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.sharedPreferences.authToken}")
                return headers
            }

        }

        App.sharedPreferences.requestQeue.add(createRequest)
    }

    fun findUserByEmail(context: Context, complete: (Boolean) -> Unit) {
        val findUserRequest = object : JsonObjectRequest(Method.GET, "$URL_GET_USER${App.sharedPreferences.userEmail}", null, Response.Listener { response ->
            try {
                UserDataService.name = response.getString("name")
                UserDataService.email = response.getString("email")
                UserDataService.avatarName = response.getString("avatarName")
                UserDataService.avatarColor = response.getString("avatarColor")
                UserDataService.id = response.getString("_id")

                val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                LocalBroadcastManager.getInstance(context).sendBroadcast(userDataChange)
                complete(true)
            } catch (e : org.json.JSONException) {
                Log.e("JSON", "EXC: ${e.localizedMessage}")
            }

        }, Response.ErrorListener { error ->
            Log.d("ERROR","Error finding user ${error.printStackTrace()}")
            complete(false)

        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }


            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.sharedPreferences.authToken}")
                return headers
            }
        }


        App.sharedPreferences.requestQeue.add(findUserRequest)
    }



}