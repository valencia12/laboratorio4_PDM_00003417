package com.example.myapplication

import android.net.Network
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tareapokemon.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var movieList: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun initReacyclerView() {
        viewManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(movieList)

        movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = movieAdapter

        }
    }

    private inner class FetchMovi : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {

            if (params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieUrl = NetworkUtils().buildUrl(movieName)

            return try {
                NetworkUtils().getResponseFromHttpUrl(movieUrl)
            } cath (e: IOException){
                ""
            }
        }

    }
}
