package com.example.moviesapilearning.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val key = "X-RapidAPI-Key: ee1e6c8509msh248a59e6e171fbcp135385jsnae105e590c7f"
const val host = "X-RapidAPI-Host: imdb8.p.rapidapi.com"

interface MoviesAPI{

    @Headers(key, host)
    @GET("actors/list-born-today")
    fun getActorsBornToday(@Query("month") month: Int, @Query("day") day: Int) : Call<BornToday>

    @Headers(key, host)
    @GET("actors/get-bio")
    fun getBio(@Query("nconst") name: String) : Call<Bio>

}
