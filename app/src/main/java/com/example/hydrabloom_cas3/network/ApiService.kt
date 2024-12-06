package com.example.hydrabloom_cas3.network


import retrofit2.http.GET
import com.example.hydrabloom_cas3.data.Plant


interface ApiService {
    @GET("plants")
    suspend fun getPlants(): List<Plant>
}
