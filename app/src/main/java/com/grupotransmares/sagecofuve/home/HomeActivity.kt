package com.grupotransmares.sagecofuve.home

import android.content.Intent
import android.os.Bundle
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseActivity
import com.grupotransmares.sagecofuve.home.agenda.AgendaFragment
import com.grupotransmares.sagecofuve.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            addFragment(R.id.content, AgendaFragment())
        }

        toolbar.setTitle(R.string.tab_agenda)

        toolbar.inflateMenu(R.menu.home)

        toolbar.setOnMenuItemClickListener {
            item ->
            if (item.itemId ==  R.id.action_settings) {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            true
        }
    }
}
