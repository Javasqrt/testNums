package com.test.numfacts.services

import retrofit2.http.GET
import retrofit2.http.Path

interface NumService {

    @GET("{num}?json")
    suspend fun getNumFact(@Path("num") num: Int): NumServiceData

    @GET("random/math?json")
    suspend fun getRandomNumFact(): NumServiceData
}
