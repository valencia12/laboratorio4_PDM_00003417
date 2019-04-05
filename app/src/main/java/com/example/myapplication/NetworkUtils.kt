package com.example.tareapokemon

import android.net.Uri
import android.view.View
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class NetworkUtility(){

    val MOVIES_API_BASEURL: String = "http://www.omdbapi.com/"

    val TOKEN_API: String = "800fbdcb"

    fun buildUrl(): URL {
        val builtUri = Uri.parse(MOVIES_API_BASEURL)
            .buildUpon()
        .appendQueryParameter("apikey", TOKEN_API)
            .appendQueryParameter("t", movieName)
            .build()

        lateinit var url: URL
        try {
            url = URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return url
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.getInputStream()
            //val `fot` = urlConnection.getInputStream()

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            if (hasInput) {
                return scanner.next()
            } else {
                return null
            }
        } finally {
            urlConnection.disconnect()
        }
    }



}