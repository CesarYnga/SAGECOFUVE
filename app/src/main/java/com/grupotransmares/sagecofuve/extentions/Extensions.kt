package com.grupotransmares.sagecofuve.extentions

import android.content.Context
import android.net.ConnectivityManager


fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}
