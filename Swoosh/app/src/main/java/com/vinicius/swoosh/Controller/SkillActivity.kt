package com.vinicius.swoosh.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.vinicius.swoosh.Utilities.EXTRA_LEAGUE
import com.vinicius.swoosh.R
import com.vinicius.swoosh.Utilities.EXTRA_SKILL
import kotlinx.android.synthetic.main.activity_skill.*

class SkillActivity : BaseActivity() {

    var league = ""
    var skill = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)
        league = intent.getStringExtra(EXTRA_LEAGUE)
        Log.i("info", league)
    }

    fun onBallerClicked(view: View) {
        beginnerSkillButton.isChecked = false

        skill = "baller"
    }

    fun onBeginnerClicked(view: View) {
        ballerSkillButton.isChecked = false

        skill = "beginner"
    }

    fun onSkillFinishClicked(view: View) {
        if (skill != "") {
            val finishActivty = Intent(this, FinishActivity::class.java)
            finishActivty.putExtra(EXTRA_LEAGUE, league)
            finishActivty.putExtra(EXTRA_SKILL, skill)
            startActivity(finishActivty)
        } else {
            Toast.makeText(this, "Please select a skill level..", Toast.LENGTH_SHORT).show()
        }
    }
}
