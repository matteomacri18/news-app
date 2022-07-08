package com.matteomacri.news.clients

import com.matteomacri.news.Constants.BASE_URL
import com.matteomacri.news.api.NewsInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {
    companion object {
        var retrofit: Retrofit? = null

        fun getRetrofitClient(): NewsInterface {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(NewsInterface::class.java)
        }

    }


}