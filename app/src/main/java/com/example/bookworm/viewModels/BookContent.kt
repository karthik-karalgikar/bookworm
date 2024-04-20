package com.example.bookworm.viewModels

import android.util.Log
import com.example.bookworm.BookData
import com.example.bookworm.api.BookApiService
import com.example.bookworm.model.BookDataResponse
import com.example.bookworm.model.VolumeInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class BookContent {

    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    fun getBookContent(){
        val gson = GsonBuilder().setLenient().create()

        val api = Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookApiService::class.java)
        Log.d("Books", "onResponse: lalala")


        api.BookApiStatus(
            "application/json",
            "*/*",
            "gzip, deflate, br",
            "keep-alive"
        ).enqueue(
            object : Callback<BookData> {
                override fun onResponse(
                    call: Call<BookData>,
                    response: Response<BookData>
                ) {
                    Log.d("Books", "onResponse1: ${response.code()}")
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        Log.i("Books", "onResponse: $responseData")
                        responseData?.let { volumeInfo ->

                            // Now you can access properties of volumeInfoObject
                            Log.d("Books", "Title: ${volumeInfo.items}")
                            Log.d("Books", "Authors: ${volumeInfo.kind}")
                            Log.d("Books", "Categories: ${volumeInfo.totalItems}")
                        }
                    } else {
                        val responseData = response.body()
                        Log.i("Books", "onResponse: $responseData")
                    }
                }

                override fun onFailure(call: Call<BookData>, t: Throwable) {
                    Log.i("Books", "onResponse: ${t.message}")
                }

            }
        )

    }
}