package com.vinicius.swoosh.Controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.vinicius.swoosh.Model.Player
import com.vinicius.swoosh.Utilities.EXTRA_LEAGUE
import com.vinicius.swoosh.R
import com.vinicius.swoosh.Utilities.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : BaseActivity() {


    var player = Player("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
    }

    fun onMensClicked(view: View) {
        womensLeagueButton.isChecked = false
        coedLeagueButton.isChecked = false

        player.league= "mens"

    }

    fun onWomensClicked(view: View) {
        mensLeagueButton.isChecked = false
        coedLeagueButton.isChecked = false

        player.league= "womens"
    }

    fun onCoedClicked(view: View) {
        womensLeagueButton.isChecked = false
        mensLeagueButton.isChecked = false

        player.league = "coed"

    }

    fun leagueNextClicked(view: View) {

        if (player.league != "") {

            val skillActivity = Intent(this, SkillActivity::class.java)
            skillActivity.putExtra(EXTRA_PLAYER,player)
            startActivity(skillActivity)
        } else {
            Toast.makeText(this, "Please select a league.", Toast.LENGTH_SHORT).show()
        }
    }
}
