package com.vinicius.swoosh.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vinicius.swoosh.Model.Player
import com.vinicius.swoosh.R
import com.vinicius.swoosh.Utilities.EXTRA_LEAGUE
import com.vinicius.swoosh.Utilities.EXTRA_PLAYER
import com.vinicius.swoosh.Utilities.EXTRA_SKILL
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeagueText.text = "Looking for ${player.league} ${player.skill} league near you.. "
    }
}
