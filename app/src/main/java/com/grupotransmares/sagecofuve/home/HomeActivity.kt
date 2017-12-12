package com.grupotransmares.sagecofuve.home

import android.os.Bundle
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseActivity
import com.grupotransmares.sagecofuve.home.agenda.AgendaFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            addFragment(R.id.content, AgendaFragment())
        }

        toolbar.setTitle(R.string.tab_agenda)
    }
}
