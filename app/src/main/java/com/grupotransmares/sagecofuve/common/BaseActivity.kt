package com.grupotransmares.sagecofuve.common

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }
}