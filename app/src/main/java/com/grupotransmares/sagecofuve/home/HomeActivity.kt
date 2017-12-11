package com.grupotransmares.sagecofuve.home

import android.os.Bundle
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseActivity
import com.grupotransmares.sagecofuve.home.agenda.AgendaFragment

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            addFragment(R.id.content, AgendaFragment())
        }
    }
}
