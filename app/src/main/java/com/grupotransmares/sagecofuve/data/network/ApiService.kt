package com.grupotransmares.sagecofuve.data.network

import com.grupotransmares.sagecofuve.home.agenda.domain.model.Visit
import com.grupotransmares.sagecofuve.tracking.domain.TrackKey
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val SERVICE_ENDPOINT = "https://0pmtdrl0g8.execute-api.us-east-2.amazonaws.com/dev/"
    }

    fun getVisits() : Call<List<Visit>>

    @POST("Locations/register")
    fun registerLocation(@Body requestValues: Map<String, @JvmSuppressWildcards Any>): Call<TrackKey>

    @POST("Locations/update")
    fun updateLocation(@Body requestValues: Map<String, @JvmSuppressWildcards Any>): Call<Unit>
}