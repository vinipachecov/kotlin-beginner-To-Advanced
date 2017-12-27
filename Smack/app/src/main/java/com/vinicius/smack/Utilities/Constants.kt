package com.vinicius.smack.Utilities

/**
 * Created by vinicius on 12/26/17.
 */

const val BASE_URL = "https://vini-chattychat.herokuapp.com/v1/"
const val SOCKET_URL = "https://vini-chattychat.herokuapp.com/"

const val URL_REGISTER = "${BASE_URL}account/register"
const val URL_LOGIN = "${BASE_URL}account/login"
const val URL_CREATE_USER = "${BASE_URL}/user/add"
const val URL_GET_USER = "${BASE_URL}user/byEmail/"

//Broadcast Constantes

const val BROADCAST_USER_DATA_CHANGE = "BROADCAST_USER_DATA_CHANGE"