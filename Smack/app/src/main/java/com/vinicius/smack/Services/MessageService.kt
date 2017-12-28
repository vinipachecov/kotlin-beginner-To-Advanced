package com.vinicius.smack.Services

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.vinicius.smack.Controller.App
import com.vinicius.smack.Model.Channel
import com.vinicius.smack.Model.Message
import com.vinicius.smack.Utilities.URL_GET_CHANNELS
import com.vinicius.smack.Utilities.URL_GET_MESSAGES
import org.json.JSONException

/**
 * Created by vinicius on 12/27/17.
 */

object MessageService {
    val channels = ArrayList<Channel>()
    val messages = ArrayList<Message>()

    fun getChannels(complete: (Boolean) -> Unit) {

        val channelRequest = object : JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null, Response.Listener { response ->

            try {

                for (x in 0 until response.length()) {
                    val channel = response.getJSONObject(x)
                    val name = channel.getString("name")
                    val channelDesc = channel.getString("description")
                    val channelId = channel.getString("_id")

                    val newChannel = Channel(name, channelDesc, channelId)
                    channels.add(newChannel)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.e("JSON", "EXC ${e.localizedMessage}")
            }

        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not retrieve Channels")
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

        App.sharedPreferences.requestQeue.add(channelRequest)

    }

    fun getMessages(channelId: String, complete: (Boolean) -> Unit) {

        val url = "${URL_GET_MESSAGES}$channelId"

        val messagesRequest = object : JsonArrayRequest(Method.GET, url, null, Response.Listener { response ->
        clearMessages()
            try {
                for (x in 0 until response.length()) {
                    val message = response.getJSONObject(x)

                    // extract information of the message

                    val messageBody = message.getString("messageBody")
                    val channelId = message.getString("channelId")
                    val id = message.getString("_id")
                    val userName = message.getString("userName")
                    val userAvatar = message.getString("userAvatar")
                    val userAvatarColor = message.getString("userAvatarColor")
                    val timeStamp = message.getString("timeStamp")

                    val newMessage = Message(messageBody, userName, channelId, userAvatar, userAvatarColor, id, timeStamp)

                    this.messages.add(newMessage)
                    complete(true)
                }

            } catch (e: JSONException) {
                Log.e("JSON", "EXC: ${e.localizedMessage}")
                complete(false)
            }


        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Error finding user ${error.printStackTrace()}")
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
        App.sharedPreferences.requestQeue.add(messagesRequest)

    }


    fun clearMessages(){
        messages.clear()
    }

    fun clearChannels(){
        channels.clear()
    }


}