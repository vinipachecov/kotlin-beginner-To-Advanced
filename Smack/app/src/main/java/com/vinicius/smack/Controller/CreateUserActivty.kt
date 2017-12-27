package com.vinicius.smack.Controller

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.Toast
import com.vinicius.smack.R
import com.vinicius.smack.Services.AuthService
import com.vinicius.smack.Services.UserDataService
import com.vinicius.smack.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class CreateUserActivty : AppCompatActivity() {

    var userAvatar = "profiledefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        createSpinner.visibility = View.INVISIBLE
    }

    fun generateUserAvatar(view: View) {
        val random = Random()
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        if (color == 0) {
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }
        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)

        createAvatarImageView.setImageResource(resourceId)

    }


    fun generateColorClicked(view: View) {
        val random = Random()

        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)


        createAvatarImageView.setBackgroundColor(Color.rgb(r, g, b))

        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savegB = b.toDouble() / 255

        avatarColor = "[$savedR, $savedG, $savegB]"
    }

    fun createUserClicked(view: View) {
        enableSpinner(true)

        val userName = createUserNameText.text.toString()
        val email = createEmailText.text.toString()
        val password = createPasswordText.text.toString()


        if (userName.isNotEmpty() && email.isNotBlank() && password.isNotBlank()) {
            AuthService.registerUser(this, email, password) { registerSuccess ->
                if (registerSuccess) {
                    Toast.makeText(this,"User Created", Toast.LENGTH_SHORT).show()
                    AuthService.loginUser(this, email,password) {loginSuccess ->
                        if(loginSuccess){
                          Toast.makeText(this,"User Created and sign in Sucess!", Toast.LENGTH_SHORT).show()
                            AuthService.createUser(this, userName ,email,avatarColor,userAvatar) { createSucess ->
                                if(createSucess) {

                                    val userDataChange  = Intent(BROADCAST_USER_DATA_CHANGE)
                                    LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)
                                    Toast.makeText(this,"User createeeed :)", Toast.LENGTH_SHORT).show()
                                    println(UserDataService.avatarName)
                                    finish()
                                } else {
                                    errorToast()
                                }

                            }
                        }
                    }
                }else {
                    errorToast()
                }
            }
        } else {
            errorToast()
        }

        enableSpinner(false)
    }

    fun enableSpinner(enable : Boolean) {
        if (enable) {
            createSpinner.visibility = View.VISIBLE
        } else{
            createSpinner.visibility = View.INVISIBLE
        }

        createUserButton.isEnabled = !enable
        createAvatarImageView.isEnabled = !enable
        backgroundColorButton.isEnabled = !enable
    }

    fun errorToast(){
        Toast.makeText(this, "Something went wrong, try again..",
                Toast.LENGTH_SHORT).show()
        enableSpinner(false)
    }


}
