package com.example.moviesapilearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapilearning.adapters.ActorAdapter
import com.example.moviesapilearning.api.Bio
import com.example.moviesapilearning.api.MoviesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar
import java.util.Date

const val BASE_URL = "https://imdb8.p.rapidapi.com/"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.actorRecyclerView).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            visibility = View.GONE
        }
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar).apply {
            visibility = View.VISIBLE
        }

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getActorsBornToday(
                Calendar.getInstance().get(Calendar.MONTH)+1,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                .awaitResponse()
            var bios: ArrayList<Bio>? = null

            if (response.isSuccessful) {
                val data = response.body()
                coroutineScope {
                    data?.forEach {
                        val bioResponse = api.getBio(it.dropLast(1).drop(6)).awaitResponse()
                        if (bioResponse.isSuccessful) {
                            bioResponse.body()?.let { bio ->
                                if (bios != null) {
                                    bios?.add(bio)
                                } else {
                                    bios = arrayListOf(bio)
                                }
                            }
                        }

                    }
                }
            }
            withContext(Dispatchers.Main){
                recyclerView.apply {
                    visibility = View.VISIBLE
                    adapter = bios?.let { ActorAdapter(it) }
                }
                progressBar.visibility = View.GONE
            }
        }
    }
}