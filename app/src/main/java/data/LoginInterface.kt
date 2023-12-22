package data

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginInterface {
    @POST("login")
    fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<LoginResponse>
}