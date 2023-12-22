package data

import retrofit2.Call
import retrofit2.http.GET


interface ArticlesInterface {

    @GET("articles")
    fun getData():Call<List<ArticlesItem>>
}