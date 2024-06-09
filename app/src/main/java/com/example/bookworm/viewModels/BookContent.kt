package com.example.bookworm.viewModels

import android.util.Log
import com.example.bookworm.BookData
import com.example.bookworm.ItemsItem
import com.example.bookworm.api.BookApiService
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

    fun getBookContent(title: String, author: String, callback: (List<ItemsItem>) -> Unit){
        val gson = GsonBuilder().setLenient().create()

        val api = Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookApiService::class.java)
        Log.d("Books", "onResponse: lalala")

        val query = buildQueryString(title, author)
        val apiKey = "AIzaSyBmf-LfwyfUQTJnT-7cMTNjDDbSt2y0p_0"

        Log.d("Books", "API Request URL: https://www.googleapis.com/books/v1/volumes?q=$query&key=$apiKey")

        api.BookApiStatus(query, apiKey).enqueue(
            object : Callback<BookData> {
                override fun onResponse(
                    call: Call<BookData>,
                    response: Response<BookData>
                ) {
                    Log.d("Books", "dzfbdxfb")
                    Log.d("Books", "onResponse1: ${response.code()}")
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        Log.i("Books", "onResponse: $responseData")
                        responseData?.let { bookData ->
//                            bookData.items?.forEach { item ->
//                                val volumeInfo = item.volumeInfo
//                                val title = volumeInfo.title
//                                val author = volumeInfo.authors?.joinToString(", ")
//                                val categories = volumeInfo.categories?.joinToString(", ")
//
//                                Log.d("Books", "Title: $title")
//                                Log.d("Books", "Authors: $author")
//                                Log.d("Books", "Categories: $categories")
//                            }

//                            // Now you can access properties of volumeInfoObject
//                            Log.d("Books", "Kind: ${bookData.kind}")
//                            Log.d("Books", "Total : ${bookData.totalItems}")
//                            Log.d("Books", "Items: ${bookData.items}")

                            callback(bookData.items ?: emptyList())
                        }
                    } else {
                        callback(emptyList())
                        Log.i("Books", "onResponse error: ${response.errorBody()?.string()}")
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

    private fun buildQueryString(title: String?, author: String?): String {
        val queryBuilder = StringBuilder()
        title?.takeIf { it.isNotBlank() }?.let {
            queryBuilder.append(it)
        }
        author?.takeIf { it.isNotBlank() }?.let {
            if (queryBuilder.isNotEmpty()) {
                queryBuilder.append("+")
            }
            queryBuilder.append("inauthor:$it")
        }
        return queryBuilder.toString()
    }
}