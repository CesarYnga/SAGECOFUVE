package com.grupotransmares.sagecofuve.tracking

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.location.*
import timber.log.Timber
import com.grupotransmares.sagecofuve.settings.SettingsActivity
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager


class TrackingService : Service() {

    val locationRequest = LocationRequest()
    lateinit  var fusedLocationClient: FusedLocationProviderClient
    var requestingLocationUpdates = false
    lateinit var locationCallback: LocationCallback

    override fun onCreate() {
        super.onCreate()

        Timber.d("onCreate")

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val intervalPref = sharedPref.getInt("location_interval", 0)
        val fastestIntervalPref = sharedPref.getInt("location_fastest_interval", 0)
        val smallestDisplacementPref = sharedPref.getInt("location_smallest_displacement", 0)

        Timber.v("Location request initialize with interval %d, fastest interval %d, smallest displacement %d",
                intervalPref, fastestIntervalPref, smallestDisplacementPref)


        locationRequest.interval = intervalPref * 1000L
        locationRequest.fastestInterval = fastestIntervalPref * 1000L
        locationRequest.smallestDisplacement = smallestDisplacementPref.toFloat()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                val locations = locationResult?.locations
                val location = locations?.get(locations.size - 1)
                Timber.v("Last location: long=" + location?.longitude + " lat=" + location?.latitude)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")
        if (!requestingLocationUpdates) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            requestingLocationUpdates = true
        }
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("onDestroy")

        fusedLocationClient.removeLocationUpdates(locationCallback)
        requestingLocationUpdates = false
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}