package com.grupotransmares.sagecofuve.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(savedInstanceState == null) {
            val fragmentTransaction = this.supportFragmentManager.beginTransaction()
            fragmentTransaction.add(android.R.id.content, SettingsFragment())
            fragmentTransaction.commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
