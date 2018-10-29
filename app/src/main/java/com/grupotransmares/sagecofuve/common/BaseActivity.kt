package com.grupotransmares.sagecofuve.common

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    fun addFragment(@IdRes containerViewId: Int, fragment: androidx.fragment.app.Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }
}