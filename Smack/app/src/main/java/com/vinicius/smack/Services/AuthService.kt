package com.vinicius.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vinicius.smack.Utilities.URL_REGISTER
import org.json.JSONObject

/**
 * Created by vinicius on 12/26/17.
 */

object AuthService {

    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {

        // create our jsonObject to be sent
        val jsonBody = JSONObject()
        jsonBody.put("email",email)
        jsonBody.put("password",password)
        val requestBody = jsonBody.toString()

//        response and error listener
        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->
            println(response)
            complete(true)
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not register a new user =/. $error")
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

}