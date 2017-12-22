package com.vinicius.swoosh.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.vinicius.swoosh.Model.Player
import com.vinicius.swoosh.Utilities.EXTRA_LEAGUE
import com.vinicius.swoosh.R
import com.vinicius.swoosh.Utilities.EXTRA_PLAYER
import com.vinicius.swoosh.Utilities.EXTRA_SKILL
import kotlinx.android.synthetic.main.activity_skill.*

class SkillActivity : BaseActivity() {

    lateinit var player: Player

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_PLAYER,player)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let{
            player = savedInstanceState.getParcelable(EXTRA_PLAYER)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)
        player = intent.getParcelableExtra(EXTRA_PLAYER)
        Log.d(TAG,"Value for the player is ${Player}")
    }

    fun onBallerClicked(view: View) {
        beginnerSkillButton.isChecked = false

        player.skill = "baller"
    }

    fun onBeginnerClicked(view: View) {
        ballerSkillButton.isChecked = false

        player.skill = "beginner"
    }

    fun onSkillFinishClicked(view: View) {
        if (player.skill != "") {
            val finishActivty = Intent(this, FinishActivity::class.java)
            finishActivty.putExtra(EXTRA_PLAYER,player)
            startActivity(finishActivty)
        } else {
            Toast.makeText(this, "Please select a skill level..", Toast.LENGTH_SHORT).show()
        }
    }
}
