package com.vinicius.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vinicius.smack.Utilities.URL_CREATE_USER
import com.vinicius.smack.Utilities.URL_LOGIN
import com.vinicius.smack.Utilities.URL_REGISTER
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by vinicius on 12/26/17.
 */

object AuthService {

    var authToken: String = ""
    var isLogged = false
    var userEmail: String = ""

    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {


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
        Volley.newRequestQueue(context).add(registerRequest)
    }


    fun loginUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {


        // create our jsonObject to be sent
        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener { response ->
            // parse the json data
            println(response)

            try {
                userEmail = response.getString("user")
                authToken = response.getString("token")
                isLogged = true
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

        Volley.newRequestQueue(context).add(loginRequest)

    }


    fun createUser(context: Context, name: String, email: String, avatarColor: String, avatarName: String, complete: (Boolean) -> Unit) {
        // create our jsonObject to be sent
        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("email", email)
        jsonBody.put("avatarName", avatarName)
        jsonBody.put("avatarColor", avatarColor)
        val requestBody = jsonBody.toString()


        val createRequest = object :JsonObjectRequest(Method.POST, URL_CREATE_USER, null,
                Response.Listener { response ->

                    try {
                        UserDataService.name = response.getString("name")
                        UserDataService.email = response.getString("email")
                        UserDataService.avatarName = response.getString("avatarName")
                        UserDataService.avatarColor= response.getString("avatarColor")
                        UserDataService.id = response.getString("_id")
                        complete(true)
                    }catch (e: JSONException){
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
                val headers = HashMap<String,String>()
                headers.put("Authorization","Bearer $authToken")
                return headers
            }

        }

        Volley.newRequestQueue(context).add(createRequest)
    }


}