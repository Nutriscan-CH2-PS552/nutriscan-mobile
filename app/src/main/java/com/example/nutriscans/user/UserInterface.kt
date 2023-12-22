package com.example.nutriscans.user

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInterface {
    @POST("login")

    fun login(
        @Body userRequest: UserRequest
    ): Call<UserResponse>
}