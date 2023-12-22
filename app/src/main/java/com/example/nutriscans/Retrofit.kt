package com.example.nutriscans

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    fun getRetroClient(): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://nutriscan-api-zfi4npkzna-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}