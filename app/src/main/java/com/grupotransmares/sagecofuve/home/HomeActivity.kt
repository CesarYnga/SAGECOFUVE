package com.grupotransmares.sagecofuve.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.grupotransmares.sagecofuve.R
import com.grupotransmares.sagecofuve.common.BaseActivity
import com.grupotransmares.sagecofuve.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initUI()
    }

    fun initUI() {
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

        pager.adapter = TabsAdapter(supportFragmentManager)

        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val scaleFactor = if (position == 0) 1f else 0f
                fabAddVisit.animate().scaleX(scaleFactor).scaleY(scaleFactor).setDuration(100).start()
            }
        })
        fabAddVisit.setOnClickListener {
            val b = AlertDialog.Builder(this)
            b.setPositiveButton("ok", null)
            b.show()
        }
    }
}
